package cn.jackuxl.kncm

import cn.hutool.core.net.URLEncodeUtil

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.entity.ApiMode.E_API
import cn.jackuxl.kncm.entity.ApiMode.LINUX_API
import cn.jackuxl.kncm.entity.ApiMode.WE_API
import cn.jackuxl.kncm.util.Crypto
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson2.JSONObject
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import kotlin.math.floor

object DataProvider {
    val cookie = ""
}

typealias Param = Map<String, String>

/*

 */
fun getRequest(
    url: String,
    data: Param,
    mode: ApiMode.ApiModeEntity,
    baseUrl: String = "https://music.163.com",
    referrer: String = "https://music.163.com"
): Request {
    FuelManager.instance.basePath = baseUrl
    var cookie = DataProvider.cookie
    val param = with(Crypto) {
        when (mode) {
            WE_API -> weApi(Json.encodeToString(data))
            E_API -> {
                val jsonObject = JSONObject.parseObject(JSON.toJSONString(data))
                val subHeader = mapOf(
                    "osver" to "",
                    "deviceId" to "",
                    "appver" to "8.7.01",
                    "versioncode" to "140", //版本号
                    "mobilename" to "", //设备model
                    "buildver" to LocalDateTime.now().toString().substring(0, 10),
                    "resolution" to "1920x1080", //设备分辨率
                    "__csrf" to "",
                    "os" to "android",
                    "channel" to "",
                    "requestId" to "${LocalDateTime.now()}_${floor(Math.random() * 1000).toString().padStart(4, '0')}",
                )
                cookie = subHeader.map { "${URLEncodeUtil.encode(it.key)}=${URLEncodeUtil.encode(it.value)}" }
                    .joinToString(";")
                jsonObject["header"] = subHeader
                eApi(url.replace("/eapi", "/api"), jsonObject.toJSONString())
            }

            LINUX_API -> linuxApi(Json.encodeToString(data))
            else -> throw RuntimeException("Illegal Api Mode")
        }
    }

    return Fuel.post(url, param)
        .header(
            "user-agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Safari/605.1.15",
        )
        .header("referrer", referrer)
        .header("cookie", cookie)
}







