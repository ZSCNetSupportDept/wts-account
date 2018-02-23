package love.sola.zscnsd.wts.account.api

import love.sola.zscnsd.wts.account.domain.Address
import love.sola.zscnsd.wts.account.domain.IspAccount
import love.sola.zscnsd.wts.account.domain.User
import love.sola.zscnsd.wts.account.domain.UserRepository
import love.sola.zscnsd.wts.account.domain.enums.Block
import love.sola.zscnsd.wts.account.domain.enums.ISP
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("profile")
class RegisterController(private val userRepository: UserRepository) {

    @PostMapping("update")
    fun update(
        @RequestParam userId: Long,
        @RequestParam username: String,
        @RequestParam phone: String,
        @RequestParam block: Block,
        @RequestParam room: String,
        @RequestParam isp: ISP,
        @RequestParam ispAccount: String,
        @AuthenticationPrincipal user: User?
    ): APIResponse {
        if (user == null) {
            return APIResponse(APIError.PERMISSION_DENIED)
        }
        if (user.username != username) {
            return APIResponse(APIError.ILLEGAL_USER_INPUT.withDetail("username doesn't match"))
        }
        user.phone = phone
        user.address = Address(block, room)
        user.account = IspAccount(isp, ispAccount)
        val updatedUser = userRepository.save(user)
        return APIResponse(updatedUser)
    }

}
