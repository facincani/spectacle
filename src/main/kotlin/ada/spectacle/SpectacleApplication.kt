package ada.spectacle

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class SpectacleApplication {
	@get:Bean
	val passwordEncoder: PasswordEncoder
		get() = BCryptPasswordEncoder()

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(SpectacleApplication::class.java, *args)
		}
	}
}
