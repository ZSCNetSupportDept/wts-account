package love.sola.zscnsd.wts.account.config.oauth2

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.CompositeTokenGranter
import org.springframework.security.oauth2.provider.TokenGranter
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import java.util.*

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(
    private val authenticationManager: AuthenticationManager,
    private val userDetailService: UserDetailsService
) : AuthorizationServerConfigurerAdapter() {

    @Bean
    fun accessTokenConverter() = JwtAccessTokenConverter().apply {
        setSigningKey("secret") //FIXME more advance configure
        accessTokenConverter = DefaultAccessTokenConverter().apply {
            setUserTokenConverter(CustomUserAuthenticationConverter())
            setIncludeGrantType(true)
        }
    }

    @Bean
    fun tokenStore() = JwtTokenStore(accessTokenConverter())

    /*
    Authorization server provides two client types
    a wechat client uses client_credentials grant type means it's trusted client
    a generic client uses password grant type so third party applications can hook into
    learn more at https://alexbilbie.com/guide-to-oauth-2-grants/
     */
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
            .withClient("wechat")
            .secret("{noop}mypass")  //FIXME more advance configure
            .authorizedGrantTypes("wechat")
            .scopes("user_info")
            .resourceIds("account", "ticket")
            .and()
            .withClient("generic")
            .secret("{noop}mypass")  //FIXME more advance configure
            .authorizedGrantTypes("password")
            .scopes("user_info")
            .resourceIds("account", "ticket")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore())
            .accessTokenConverter(accessTokenConverter())
            .authenticationManager(authenticationManager)
            .reconfigureTokenGranter()
    }

    private fun AuthorizationServerEndpointsConfigurer.reconfigureTokenGranter(): AuthorizationServerEndpointsConfigurer {
        val clientDetails = clientDetailsService
        val tokenServices = tokenServices
        val authorizationCodeServices = authorizationCodeServices
        val requestFactory = oAuth2RequestFactory

        val tokenGranters = ArrayList<TokenGranter>()
        tokenGranters.add(
            AuthorizationCodeTokenGranter(
                tokenServices, authorizationCodeServices, clientDetails,
                requestFactory
            )
        )
        tokenGranters.add(RefreshTokenGranter(tokenServices, clientDetails, requestFactory))
        tokenGranters.add(ImplicitTokenGranter(tokenServices, clientDetails, requestFactory))
        tokenGranters.add(
            ClientCredentialsTokenGranter(
                tokenServices,
                clientDetails,
                requestFactory
            )
        )
        tokenGranters.add(
            ResourceOwnerPasswordTokenGranter(
                authenticationManager, tokenServices,
                clientDetails, requestFactory
            )
        )
        tokenGranters.add(
            WechatTokenGranter(
                userDetailService,
                tokenServices,
                clientDetails,
                requestFactory
            )
        )
        val tokenGranter = CompositeTokenGranter(tokenGranters)
        tokenGranter(tokenGranter)
        return this
    }
}
