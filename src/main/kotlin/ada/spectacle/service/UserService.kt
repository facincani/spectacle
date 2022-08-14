package ada.spectacle.service

import ada.spectacle.domain.User

interface UserService {

    fun creteUser(user: User): User

}