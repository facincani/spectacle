package ada.spectacle.domain

import ada.spectacle.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(val usuario: UserEntity) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return arrayListOf()
    }

    override fun getPassword(): String {
        return usuario.password
    }

    override fun getUsername(): String {
        return usuario.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}