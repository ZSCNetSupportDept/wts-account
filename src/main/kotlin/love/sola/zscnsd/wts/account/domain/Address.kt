package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.Block
import javax.persistence.Embeddable

@Embeddable
data class Address(val block: Block, val room: String)
