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
            .authorizedGrantTypes("client_credentials")
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
    }
}
