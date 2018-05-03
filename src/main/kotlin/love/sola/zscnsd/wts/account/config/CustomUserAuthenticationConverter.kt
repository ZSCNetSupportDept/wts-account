package love.sola.zscnsd.wts.account.config

import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter
import org.springframework.stereotype.Component

@Component
class CustomUserAuthenticationConverter : UserAuthenticationConverter {

    override fun extractAuthentication(map: MutableMap<String, *>): Authentication? {
        TODO()
    }

    override fun convertUserAuthentication(userAuthentication: Authentication): MutableMap<String, *> {
        TODO()
    }

}
