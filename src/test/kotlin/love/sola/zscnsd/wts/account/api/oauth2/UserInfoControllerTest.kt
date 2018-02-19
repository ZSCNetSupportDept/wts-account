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
import org.springframework.boot.test.web.client.getForObject
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserInfoControllerTest {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `user info should return user's info`() {
        userRepository.save(REGISTERED_USER)
        val token = restTemplate.getAccessToken(OAuth2Clients.WECHAT, REGISTERED_USER)!!
        val user = restTemplate.getForObject<User>("/oauth2/user?access_token={token}", token.value)
        println(user)
        Assert.assertNotNull(user)
    }

}
