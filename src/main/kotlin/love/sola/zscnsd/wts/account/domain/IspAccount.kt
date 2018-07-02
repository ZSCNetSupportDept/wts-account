package love.sola.zscnsd.wts.account.domain

import love.sola.zscnsd.wts.account.domain.enums.ISP

data class IspAccount(val isp: ISP, val account: String)
