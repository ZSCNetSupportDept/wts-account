package love.sola.zscnsd.wts.account.config

import love.sola.zscnsd.wts.account.domain.Operator
import love.sola.zscnsd.wts.account.domain.User
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter
import org.springframework.stereotype.Component

@Component
class CustomUserAuthenticationConverter : UserAuthenticationConverter {

    override fun extractAuthentication(map: Map<String, *>): Authentication? {
        TODO()
    }

    override fun convertUserAuthentication(userAuthentication: Authentication): Map<String, *> {
        val user = userAuthentication.principal as? User
                ?: throw IllegalArgumentException("Authentication's principal should be User.")
        val response = hashMapOf<String, Any?>()
        response["id"] = user.id
        response["name"] = user.name
        response["address"] = user.address
        response["phone"] = user.phone
        response["account"] = user.account
        if (user is Operator) {
            response["stuff_id"] = user.stuffId
            response["arrangement"] = user.arrangement
            response["permissions"] = user.permissions
        }
        return response
    }

}
