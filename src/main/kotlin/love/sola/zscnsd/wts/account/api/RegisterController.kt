package love.sola.zscnsd.wts.account.api

import love.sola.zscnsd.wts.account.domain.UserRepository
import love.sola.zscnsd.wts.account.domain.enums.Block
import love.sola.zscnsd.wts.account.domain.enums.ISP
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("register")
class RegisterController(val userRepository: UserRepository) {

    @PostMapping("wechat")
    fun wechat(
        @RequestParam userId: Long,
        @RequestParam username: String,
        @RequestParam wechat: String,
        @RequestParam phone: String,
        @RequestParam block: Block,
        @RequestParam room: String,
        @RequestParam isp: ISP,
        @RequestParam netAccount: String
    ) {
        val user = userRepository.findById(userId).orElse(null)
        if (user.name != username) throw IllegalArgumentException("username doesn't match")
        TODO()
    }

}
