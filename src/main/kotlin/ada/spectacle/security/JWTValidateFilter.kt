package ada.spectacle.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import java.util.ArrayList
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTValidateFilter(authenticationManager: AuthenticationManager?) : BasicAuthenticationFilter(authenticationManager) {
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {
        val atributo = request.getHeader(HEADER_ATRIBUTO)
        if (atributo == null) {
            chain.doFilter(request, response)
            return
        }
        if (!atributo.startsWith(ATRIBUTO_PREFIXO)) {
            chain.doFilter(request, response)
            return
        }
        val token = atributo.replace(ATRIBUTO_PREFIXO, "")
        val authenticationToken = getAuthenticationToken(token)
        SecurityContextHolder.getContext().authentication = authenticationToken
        chain.doFilter(request, response)
    }

    private fun getAuthenticationToken(token: String): UsernamePasswordAuthenticationToken? {
        val usuario = JWT.require(Algorithm.HMAC512(JWTAuthFilter.TOKEN_SENHA))
                .build()
                .verify(token)
                .subject ?: return null
        return UsernamePasswordAuthenticationToken(usuario, null, ArrayList())
    }

    companion object {
        const val HEADER_ATRIBUTO = "Authorization"
        const val ATRIBUTO_PREFIXO = "Bearer "
    }
}