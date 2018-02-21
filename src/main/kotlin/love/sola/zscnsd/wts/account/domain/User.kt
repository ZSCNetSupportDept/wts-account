package love.sola.zscnsd.wts.account.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(
    "username",
    "password",
    "authorities",
    "enabled",
    "accountNonLocked",
    "accountNonExpired",
    "credentialsNonExpired"
)
open class User(
    @Id val id: Long,
    val name: String,
    private var password: String?,
    var phone: String?,
    var address: Address?,
    var account: IspAccount?
) : UserDetails {

    companion object {
        private val ROLE_USER = SimpleGrantedAuthority("ROLE_USER")
    }

    override fun getUsername() = id.toString() //We use id as identity

    override fun getPassword() = password

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> = listOf(ROLE_USER)

    override fun isEnabled() = true

    override fun isCredentialsNonExpired() = true

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

}

