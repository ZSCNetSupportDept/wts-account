package love.sola.zscnsd.wts.account.api

data class APIResponse(val data: Any?, val error: APIError?, val meta: Any?) {

    companion object {
        val EMPTY = APIResponse()
    }

    constructor(data: Any? = null, meta: Any? = null) : this(data, null, meta)

    constructor(error: APIError, meta: Any? = null) : this(null, error, meta)

}

data class APIError(
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
