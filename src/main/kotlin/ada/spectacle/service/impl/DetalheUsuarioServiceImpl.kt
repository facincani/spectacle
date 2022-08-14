package ada.spectacle.service.impl

import ada.spectacle.domain.UserDetail
import ada.spectacle.entity.UserEntity
import ada.spectacle.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class DetalheUsuarioServiceImpl(repository: UserRepository) : UserDetailsService {
    private val repository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val usuario: Optional<UserEntity> = repository.findByLogin(username)
        if (usuario.isEmpty()) {
            throw UsernameNotFoundException("Usuário [$username] não encontrado")
        }
        return UserDetail(usuario.get())
    }

    init {
        this.repository = repository
    }
}
