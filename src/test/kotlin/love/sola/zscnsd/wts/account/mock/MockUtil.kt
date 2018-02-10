package love.sola.zscnsd.wts.account.mock

import love.sola.zscnsd.wts.account.domain.*
import love.sola.zscnsd.wts.account.domain.enums.Block
import love.sola.zscnsd.wts.account.domain.enums.ISP
import java.time.DayOfWeek

internal val UNREGISTERED_USER = User(
    2015000000001,
    "Sola",
    null,
    null,
    null,
    null
)
internal val UNREGISTERED_OPERATOR = Operator(
    1501,
    null,
    emptyList(),
    2015000000001,
    "Sola",
    null,
    null,
    null,
    null
)
internal val REGISTERED_USER = User(
    2015000000001,
    "Sola",
    "{noop}test",
    "15000000000",
    Address(Block.QT_18, "533"),
    IspAccount(ISP.CHINA_TELECOM, "15000000000")
)
internal val REGISTERED_OPERATOR = Operator(
    1501,
    DutyArrangement(DayOfWeek.FRIDAY, Block.XH_A),
    emptyList(),
    2015000000001,
    "Sola",
    "{noop}test",
    "15000000000",
    Address(Block.QT_18, "533"),
    IspAccount(ISP.CHINA_TELECOM, "15000000000")
)


