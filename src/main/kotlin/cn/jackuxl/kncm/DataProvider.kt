package cn.jackuxl.kncm

import cn.jackuxl.kncm.util.Crypto
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object DataProvider {
    val cookie = ""
}

inline fun <reified T> getRequest(url: String, data: Param<T>, referrer: String): Request {
    return Fuel.post(url, Crypto.weApi(Json.encodeToString(data)))
        .header(
            "user-agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Safari/605.1.15"
        )
        .header("referrer", referrer)
        .header("cookie", DataProvider.cookie)
}

typealias Param<T> = Map<String, T>