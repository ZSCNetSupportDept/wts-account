package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.ISP
import javax.persistence.Embeddable

@Embeddable
data class IspAccount(val isp: ISP, val account: String)
