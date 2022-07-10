package cn.jackuxl.ncm

import cn.jackuxl.ncm.entity.UrlParamPair
import cn.jackuxl.ncm.util.JSSecret
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.core.Request
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object DataProvider {
    val cookie = ""
}

fun <T> getRequest(url:String, data:UrlParamPair<T>, referrer:String): Request {
    return Fuel.post(url, JSSecret.transData(Json.encodeToString(data.params)))
        .header("user-agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Safari/605.1.15")
        .header("referrer",referrer)
        .header("cookie",DataProvider.cookie)
}