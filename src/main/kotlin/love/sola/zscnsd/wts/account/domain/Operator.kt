package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.Block
import org.codehaus.jackson.annotate.JsonIgnore
import java.time.DayOfWeek
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Operator(
    var access: Int,
    var block: Block?,
    var week: DayOfWeek?,
    @JsonIgnore var password: String?
) : User {

}
