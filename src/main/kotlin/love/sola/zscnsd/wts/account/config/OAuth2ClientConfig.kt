package love.sola.zscnsd.wts.account.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore

@Configuration
class OAuth2ClientConfig(val tokenStore: TokenStore) : ResourceServerConfigurerAdapter() {

    @Bean
    fun tokenServices() = DefaultTokenServices().apply {
        setTokenStore(tokenStore)
        setSupportRefreshToken(true)
    }

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId("account")
    }


    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers().permitAll()
    }
}
