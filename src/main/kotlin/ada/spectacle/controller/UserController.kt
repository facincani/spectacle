package ada.spectacle.controller

import ada.spectacle.domain.User
import ada.spectacle.entity.UserEntity
import ada.spectacle.repository.UserRepository
import ada.spectacle.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/user")
class UserController(val service: UserService, val encoder: PasswordEncoder) {

    @PostMapping
    fun create(@RequestBody user: User): ResponseEntity<String> {
        user.password = encoder.encode(user.password)
        service.creteUser(user)
        return ResponseEntity.created(URI("")).build()
    }



}