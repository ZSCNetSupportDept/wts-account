package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.Block
import org.codehaus.jackson.annotate.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    @Id val id: Long,
    val name: String,
    @JsonIgnore var wechat: String?,
    var phone: String?,
    var block: Block?,
    var room: String?,
    var account: IspAccount?
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEnabled(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsername(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPassword(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

