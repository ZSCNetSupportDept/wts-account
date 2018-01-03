package love.sola.zscnsd.wts.account.domain

import org.codehaus.jackson.annotate.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class User(
    @Id val id: Long,
    private val username: String,
    @JsonIgnore var wechat: String?,
    var phone: String?,
    var address: Address?,
    var account: IspAccount?
) : UserDetails {

    companion object {
        private val ROLE_USER = SimpleGrantedAuthority("ROLE_USER")
    }

    override fun getAuthorities(): Collection<GrantedAuthority> = listOf(ROLE_USER)

    override fun isEnabled() = true

    override fun getUsername() = username

    override fun isCredentialsNonExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPassword(): String? = null

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

}

