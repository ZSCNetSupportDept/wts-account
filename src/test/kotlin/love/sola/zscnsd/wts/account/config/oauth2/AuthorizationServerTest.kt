package love.sola.zscnsd.wts.account.config.oauth2

import love.sola.zscnsd.wts.account.domain.UserRepository
import love.sola.zscnsd.wts.account.util.OAuth2Clients
import love.sola.zscnsd.wts.account.util.REGISTERED_OPERATOR
import love.sola.zscnsd.wts.account.util.getAccessToken
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorizationServerTest {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `generic OAuth2 client should login correctly`() {
        userRepository.save(REGISTERED_OPERATOR)
        val token = restTemplate.getAccessToken(OAuth2Clients.GENERIC, REGISTERED_OPERATOR)
        println("token = $token")
        Assert.assertNotNull(token)
    }

    @Test
    fun `wechat OAuth2 client should login correctly`() {
        userRepository.save(REGISTERED_OPERATOR)
        val token = restTemplate.getAccessToken(OAuth2Clients.WECHAT, REGISTERED_OPERATOR)!!
        println("token = ${token.value}")
        Assert.assertNotNull(token.value)
    }

    @Test
    @Ignore("We are not using RSA key yet.") //FIXME
    fun `endpoint token_key should work correctly`() {
        val response = restTemplate.withBasicAuth("generic", "mypass")
            .getForEntity<String>("/oauth/token_key")
            .body
        println("response = $response")
        Assert.assertNotNull(response)
    }

}
