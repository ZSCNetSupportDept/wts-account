package love.sola.zscnsd.wts.account.api.rest

import love.sola.zscnsd.wts.account.domain.User
import love.sola.zscnsd.wts.account.domain.UserRepository
import love.sola.zscnsd.wts.common.domain.Address
import love.sola.zscnsd.wts.common.domain.Block
import love.sola.zscnsd.wts.common.domain.ISP
import love.sola.zscnsd.wts.common.domain.IspAccount
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/rest/profile")
class ProfileController(private val userRepository: UserRepository) {

    @PostMapping("update")
    fun update(
        @RequestParam phone: String,
        @RequestParam block: Block,
        @RequestParam room: String,
        @RequestParam isp: ISP,
        @RequestParam ispAccount: String,
        @AuthenticationPrincipal user: User
    ): User {
        user.phone = phone
        user.address = Address(block, room)
        user.account = IspAccount(isp, ispAccount)
        return userRepository.save(user)
    }

}
