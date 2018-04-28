package love.sola.zscnsd.wts.account.config

import love.sola.zscnsd.wts.account.domain.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class JpaUserDetailService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        TODO()
    }

}
