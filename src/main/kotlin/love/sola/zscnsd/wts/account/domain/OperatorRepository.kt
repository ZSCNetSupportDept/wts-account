package love.sola.zscnsd.wts.account.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OperatorRepository : JpaRepository<Operator, Int> {

}
