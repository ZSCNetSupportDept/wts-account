package love.sola.zscnsd.wts.account.config

import love.sola.zscnsd.wts.account.domain.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class JpaUserDetailService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        try {
            val id = username.toLong()
            val user = userRepository.findById(id)
            if (user.isPresent) {
                return user.get()
            } else {
                throw UsernameNotFoundException("id: $id doesn't exist")
            }
        } catch (e: NumberFormatException) {
            throw UsernameNotFoundException("not a number", e)
        }
    }

}
