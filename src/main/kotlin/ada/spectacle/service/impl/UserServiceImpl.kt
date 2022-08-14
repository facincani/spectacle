package ada.spectacle.service.impl

import ada.spectacle.domain.User
import ada.spectacle.domain.toUserEntity
import ada.spectacle.repository.UserRepository
import ada.spectacle.service.UserService
import org.springframework.stereotype.Component

@Component
class UserServiceImpl(val repository: UserRepository): UserService {
    override fun creteUser(user: User): User {
        repository.save(user.toUserEntity())
        return user
    }
}