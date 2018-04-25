package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.Block
import love.sola.zscnsd.wts.account.domain.enums.ISP
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
    @Id val id: Long,
    var wechat: String?,
    val name: String,
    var phone: String?,
    var block: Block?,
    var room: String?,
    var isp: ISP?,
    var netAccount: String?
){

}

