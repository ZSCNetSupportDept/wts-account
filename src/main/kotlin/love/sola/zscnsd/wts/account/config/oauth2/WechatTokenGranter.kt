package love.sola.zscnsd.wts.account.config.oauth2

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException
import org.springframework.security.oauth2.provider.*
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices

class WechatTokenGranter(
    private val userDetailsService: UserDetailsService,
    tokenServices: AuthorizationServerTokenServices,
    clientDetailsService: ClientDetailsService,
    requestFactory: OAuth2RequestFactory
) : AbstractTokenGranter(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE) {

    companion object {
        private const val GRANT_TYPE = "wechat"
    }

    override fun getOAuth2Authentication(
        client: ClientDetails,
        tokenRequest: TokenRequest
    ): OAuth2Authentication {
        val parameters = LinkedHashMap(tokenRequest.requestParameters)
        val username = parameters["username"]
        // Protect from downstream leaks of password
        parameters.remove("password")

        if (client.clientId != "wechat") {
            throw InvalidGrantException("Wechat client only, got: ${client.clientId}")
        }

        val loadedUser = userDetailsService.loadUserByUsername(username)
                ?: throw InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation"
                )

        val userAuth =
            UsernamePasswordAuthenticationToken(loadedUser, "N/A", loadedUser.authorities)
        (userAuth as AbstractAuthenticationToken).details = parameters

        val storedOAuth2Request = requestFactory.createOAuth2Request(client, tokenRequest)
        return OAuth2Authentication(storedOAuth2Request, userAuth)
    }
}
