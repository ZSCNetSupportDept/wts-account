package love.sola.zscnsd.wts.account.api

class APIResponse {

    companion object {
        val EMPTY = APIResponse()
    }

    val data: Any?
    val error: APIError?
    val meta: Any?

    constructor(data: Any? = null, meta: Any? = null) {
        this.data = data
        this.error = null
        this.meta = meta
    }

    constructor(error: APIError, meta: Any? = null) {
        this.data = null
        this.error = error
        this.meta = meta
    }

}

class APIError(
    val code: Int,
    val title: String,
    val detail: String? = title,
    val meta: Any? = null
) {
    companion object {
        val ILLEGAL_USER_INPUT = APIError(40001, "illegal user input")
        val PERMISSION_DENIED = APIError(40001, "permission denied")
    }

    fun withDetail(detail: String) = APIError(code, title, detail, meta)
}
