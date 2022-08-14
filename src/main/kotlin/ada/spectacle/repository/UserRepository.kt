package ada.spectacle.repository

import ada.spectacle.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findByLogin(login: String): Optional<UserEntity>
}