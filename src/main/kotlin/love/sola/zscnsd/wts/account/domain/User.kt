package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.Block
import love.sola.zscnsd.wts.account.domain.enums.ISP
import org.springframework.data.annotation.Id


data class User(
    @Id val id: Long,
    var wechat: String,
    val name: String,
    var phone: String,
    var block: Block,
    var room: String,
    var isp: ISP,
    var netAccount: String
)

