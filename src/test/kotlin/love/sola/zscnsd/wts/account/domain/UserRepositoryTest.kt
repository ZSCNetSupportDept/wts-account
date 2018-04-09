package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.util.UNREGISTERED_OPERATOR
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    fun `UserRepository should be able to load Operators`() {
        userRepository.save(UNREGISTERED_OPERATOR)
        val loadedUser = userRepository.findById(UNREGISTERED_OPERATOR.id).get()
        Assert.assertTrue(loadedUser is Operator)
    }

}
