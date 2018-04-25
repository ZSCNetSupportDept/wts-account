package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.Block
import java.time.DayOfWeek
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Operator(
    @Id val id: Int,
    val name: String,
    var access: Int,
    var wechat: String?,
    var block: Block?,
    var week: DayOfWeek?,
    var password: String?
) {

}
