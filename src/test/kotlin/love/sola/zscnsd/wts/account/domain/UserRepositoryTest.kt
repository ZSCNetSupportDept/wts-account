package love.sola.zscnsd.wts.account.domain

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun `UserRepository should be able to load Operators`() {
        val mockOperator = Operator(
            1501,
            null,
            null,
            emptyList(),
            2015001010101,
            "Sola",
            null,
            null,
            null
        )
        userRepository.save(mockOperator)
        val loadedUser = userRepository.findById(2015001010101).get()
        Assert.assertTrue(loadedUser is Operator)
    }

}
