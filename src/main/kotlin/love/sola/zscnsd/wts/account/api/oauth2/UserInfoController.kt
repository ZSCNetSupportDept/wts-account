package love.sola.zscnsd.wts.account.api.oauth2

import love.sola.zscnsd.wts.account.domain.User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("oauth2")
class UserInfoController {

    @GetMapping("user")
    fun user(@AuthenticationPrincipal user: User?) = user

}
