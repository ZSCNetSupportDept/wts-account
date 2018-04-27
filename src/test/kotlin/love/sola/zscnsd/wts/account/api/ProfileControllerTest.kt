package love.sola.zscnsd.wts.account.api

import love.sola.zscnsd.wts.account.domain.UserRepository
import love.sola.zscnsd.wts.account.util.OAuth2Clients
import love.sola.zscnsd.wts.account.util.REGISTERED_USER
import love.sola.zscnsd.wts.account.util.getAccessToken
import love.sola.zscnsd.wts.common.domain.Block
import love.sola.zscnsd.wts.common.domain.ISP
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForObject
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.LinkedMultiValueMap

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProfileControllerTest {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    fun `update should work properly`() {
        userRepository.save(REGISTERED_USER)
        val token = restTemplate.getAccessToken(OAuth2Clients.WECHAT, REGISTERED_USER)!!
        val response = restTemplate.postForObject<APIResponse>(
            "/profile/update?access_token={token}",
            LinkedMultiValueMap<String, String>().apply {
                this["phone"] = "16677778888"
                this["block"] = "XH_B"
                this["room"] = "1229"
                this["isp"] = "CHINA_MOBILE"
                this["ispAccount"] = "16677778888"
            },
            token.value
        )
        assertNotNull(response)
        val updatedUser = userRepository.findById(REGISTERED_USER.id).get()
        assertEquals(updatedUser.phone, "16677778888")
        assertEquals(updatedUser.address!!.block, Block.XH_B)
        assertEquals(updatedUser.address!!.room, "1229")
        assertEquals(updatedUser.account!!.isp, ISP.CHINA_MOBILE)
        assertEquals(updatedUser.account!!.account, "16677778888")
    }

}
