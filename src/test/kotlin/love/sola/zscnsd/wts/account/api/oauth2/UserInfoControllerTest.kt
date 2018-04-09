package love.sola.zscnsd.wts.account.api.oauth2

import love.sola.zscnsd.wts.account.domain.User
import love.sola.zscnsd.wts.account.domain.UserRepository
import love.sola.zscnsd.wts.account.util.OAuth2Clients
import love.sola.zscnsd.wts.account.util.REGISTERED_USER
import love.sola.zscnsd.wts.account.util.getAccessToken
import org.junit.Assert
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.getForObject
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserInfoControllerTest {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    fun `user info should return user's info`() {
        userRepository.save(REGISTERED_USER)
        val token = restTemplate.getAccessToken(OAuth2Clients.WECHAT, REGISTERED_USER)!!
        val user = restTemplate.getForObject<User>("/oauth2/user?access_token={token}", token.value)
        println(user)
        Assert.assertNotNull(user)
    }

    @Test
    fun `request without authentication should return 401`() {
        val response = restTemplate.getForEntity<String>("/oauth2/user")
        Assert.assertNotNull(response)
        Assert.assertEquals(response.statusCode, HttpStatus.UNAUTHORIZED)
    }

}
