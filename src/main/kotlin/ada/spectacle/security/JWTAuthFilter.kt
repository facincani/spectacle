package ada.spectacle.security

import ada.spectacle.domain.UserDetail
import ada.spectacle.entity.UserEntity
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.lang.RuntimeException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthFilter(@get:JvmName("getAdapterContext") private val authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {
    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest,
                                       response: HttpServletResponse): Authentication {
        return try {
            val usuario: UserEntity = ObjectMapper()
                    .readValue(request.inputStream, UserEntity::class.java)
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                    usuario.login,
                    usuario.password,
                    ArrayList()
            ))
        } catch (e: IOException) {
            throw RuntimeException("Falha ao autenticar usuario", e)
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          chain: FilterChain,
                                          authResult: Authentication) {
        val usuarioData: UserDetail = authResult.principal as UserDetail
        val token = JWT.create().withSubject(usuarioData.getUsername())
                .withExpiresAt(Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_SENHA))
        response.writer.write(token)
        response.writer.flush()
    }

    companion object {
        const val TOKEN_EXPIRACAO = 600000
        const val TOKEN_SENHA = "463408a1-54c9-4307-bb1c-6cced559f5a7"
    }
}