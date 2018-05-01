package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.Block
import love.sola.zscnsd.wts.account.domain.enums.ISP
import org.codehaus.jackson.annotate.JsonIgnore
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
    @Id val id: Long,
    @JsonIgnore var wechat: String?,
    val name: String,
    var phone: String?,
    var block: Block?,
    var room: String?,
    var isp: ISP?,
    var netAccount: String?
){

}

