package love.sola.zscnsd.wts.account.config

import love.sola.zscnsd.wts.account.domain.UserRepository
import love.sola.zscnsd.wts.account.util.REGISTERED_OPERATOR
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.LinkedMultiValueMap

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OAuth2ServerTest {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `generic OAuth2 client should login correctly`() {
        userRepository.save(REGISTERED_OPERATOR)
        val response = restTemplate.withBasicAuth("generic", "mypass")
            .postForEntity<String>("/oauth/token", LinkedMultiValueMap<String, String>().apply {
                put("grant_type", listOf("password"))
                put("username", listOf(REGISTERED_OPERATOR.id.toString()))
                put("password", listOf(REGISTERED_OPERATOR.password!!.substring("{noop}".length)))
            })
            .body
        println("response = $response")
        Assert.assertNotNull(response)
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
