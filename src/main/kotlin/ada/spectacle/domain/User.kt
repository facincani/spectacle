package ada.spectacle.domain

import ada.spectacle.entity.UserEntity


data class User(
        var login: String,
        var password: String,
)

fun User.toUserEntity() = UserEntity(null, login, password, null, null)