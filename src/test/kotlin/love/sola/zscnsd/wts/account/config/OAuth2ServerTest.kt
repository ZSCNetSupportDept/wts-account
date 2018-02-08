package love.sola.zscnsd.wts.account.config

import love.sola.zscnsd.wts.account.domain.DutyArrangement
import love.sola.zscnsd.wts.account.domain.Operator
import love.sola.zscnsd.wts.account.domain.UserRepository
import love.sola.zscnsd.wts.account.domain.enums.Block
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
import java.time.DayOfWeek

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OAuth2ServerTest {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `generic OAuth2 client should login correctly`() {
        val mockOperator = Operator(
            1501,
            DutyArrangement(DayOfWeek.MONDAY, Block.XH_A),
            emptyList(),
            2015000000001,
            "Sola",
            "{noop}test",
            null,
            null,
            null
        )
        userRepository.save(mockOperator)
        val response = restTemplate.withBasicAuth("generic", "mypass")
            .postForEntity<String>("/oauth/token", LinkedMultiValueMap<String, String>().apply {
                put("grant_type", listOf("password"))
                put("username", listOf("2015000000001"))
                put("password", listOf("test"))
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
