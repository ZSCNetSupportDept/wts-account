package love.sola.zscnsd.wts.account.config.oauth2

import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import love.sola.zscnsd.wts.account.domain.Operator
import love.sola.zscnsd.wts.account.domain.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter

class CustomUserAuthenticationConverter : UserAuthenticationConverter {

    private val objectMapper = jacksonObjectMapper().findAndRegisterModules()

    override fun convertUserAuthentication(userAuthentication: Authentication): Map<String, *> {
        val user = userAuthentication.principal as? User
                ?: throw IllegalArgumentException("Authentication's principal should be User.")
        val response = hashMapOf<String, Any?>()
        if (user is Operator) {
            response["operator"] = user
        } else {
            response["user"] = user
        }
        return response
    }

    override fun extractAuthentication(map: Map<String, *>): Authentication? {
        val user = when {
            "operator" in map -> objectMapper.convertValue<Operator>(map["operator"]!!)
            "user" in map -> objectMapper.convertValue<User>(map["user"]!!)
            else -> null
        }
        return if (user != null) {
            UsernamePasswordAuthenticationToken(user, "N/A", user.authorities)
        } else {
            null
        }
    }

}
