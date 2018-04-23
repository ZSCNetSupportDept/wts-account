package love.sola.zscnsd.wts.account.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableAuthorizationServer
class OAuth2ServerConfig(val authenticationManager: AuthenticationManager) : AuthorizationServerConfigurerAdapter() {

    @Bean
    fun accessTokenConverter() = JwtAccessTokenConverter().apply {
        setSigningKey("secret") //FIXME more advance configure
    }

    @Bean
    fun tokenStore() = JwtTokenStore(accessTokenConverter())

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
            .withClient("test")
            .secret("{noop}pass:p")
            .authorizedGrantTypes("password")
            .scopes("user_info")
            .resourceIds("account", "ticket")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore())
            .accessTokenConverter(accessTokenConverter())
            .authenticationManager(authenticationManager)
    }
}
