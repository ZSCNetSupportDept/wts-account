package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.Block
import java.time.DayOfWeek
import javax.persistence.Embeddable

@Embeddable
data class DutyArrangement(val week: DayOfWeek, val block: Block)
