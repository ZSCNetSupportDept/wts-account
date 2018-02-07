package love.sola.zscnsd.wts.account.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType

@Entity
class Operator(
    val stuffId: Int,
    var arrangement: DutyArrangement?,
    @ElementCollection(fetch = FetchType.EAGER)
    var permissions: List<String>,
    id: Long,
    username: String,
    password: String?,
    phone: String?,
    address: Address?,
    account: IspAccount?
) : User(id, username, password, phone, address, account) {

    companion object {
        private val ROLE_OPERATOR = SimpleGrantedAuthority("ROLE_OPERATOR")
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        //TODO cache this result if needed.
        return super.getAuthorities() + ROLE_OPERATOR + permissions.map { SimpleGrantedAuthority(it) }
    }

}
