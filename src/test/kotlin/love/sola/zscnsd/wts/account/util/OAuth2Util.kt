package love.sola.zscnsd.wts.account.util

import love.sola.zscnsd.wts.account.domain.User
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.util.LinkedMultiValueMap

enum class OAuth2Clients(val id: String, val secret: String, val grantType: String) {
    GENERIC("generic", "mypass", "password"),
    WECHAT("wechat", "mypass", "wechat")
}

fun TestRestTemplate.getAccessToken(client: OAuth2Clients, user: User): OAuth2AccessToken? =
    postForEntity<OAuth2AccessToken>("/oauth/token", LinkedMultiValueMap<String, String>().apply {
        set("grant_type", client.grantType)
        set("username", user.id.toString())
        set("password", user.password)
    }).body
