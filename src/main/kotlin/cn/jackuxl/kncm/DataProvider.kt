package cn.jackuxl.kncm

import cn.hutool.core.net.URLEncodeUtil
import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.entity.ApiMode.E_API
import cn.jackuxl.kncm.entity.ApiMode.LINUX_API
import cn.jackuxl.kncm.entity.ApiMode.WE_API
import cn.jackuxl.kncm.entity.UserAgentMode
import cn.jackuxl.kncm.entity.UserAgentMode.UserAgentModeEntity
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
    var cookie: Map<String, String> = mapOf()
    var realIP: String? = null
}

fun randomUserAgent(mode: UserAgentModeEntity): String? {
    val mobileUserAgentList = listOf(
        // iOS 13.5.1 14.0 beta with Safari
        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.1 Mobile/15E148 Safari/604.1",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.",
        // iOS with qq micromsg
        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/602.1.50 (KHTML like Gecko) Mobile/14A456 QQ/6.5.7.408 V1_IPH_SQ_6.5.7_1_APP_A Pixel/750 Core/UIWebView NetType/4G Mem/103",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.15(0x17000f27) NetType/WIFI Language/zh",
        // Android -> HuaWei Xiaomi
        "Mozilla/5.0 (Linux; Android 9; PCT-AL10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.64 HuaweiBrowser/10.0.3.311 Mobile Safari/537.36",
        "Mozilla/5.0 (Linux; U; Android 9; zh-cn; Redmi Note 8 Build/PKQ1.190616.001) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/71.0.3578.141 Mobile Safari/537.36 XiaoMi/MiuiBrowser/12.5.22",
        // Android + qq micromsg
        "Mozilla/5.0 (Linux; Android 10; YAL-AL00 Build/HUAWEIYAL-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/78.0.3904.62 XWEB/2581 MMWEBSDK/200801 Mobile Safari/537.36 MMWEBID/3027 MicroMessenger/7.0.18.1740(0x27001235) Process/toolsmp WeChat/arm64 NetType/WIFI Language/zh_CN ABI/arm64",
        "Mozilla/5.0 (Linux; U; Android 8.1.0; zh-cn; BKK-AL10 Build/HONORBKK-AL10) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/66.0.3359.126 MQQBrowser/10.6 Mobile Safari/537.36",
    )
    val pcUserAgentList = listOf(
        // macOS 10.15.6  Firefox / Chrome / Safari
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:80.0) Gecko/20100101 Firefox/80.0",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.30 Safari/537.36",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.2 Safari/605.1.15",
        // Windows 10 Firefox / Chrome / Edge
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:80.0) Gecko/20100101 Firefox/80.0",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.30 Safari/537.36",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/13.10586",
    )
    return when (mode) {
        UserAgentMode.PC -> pcUserAgentList.random()
        UserAgentMode.MOBILE -> mobileUserAgentList.random()
        UserAgentMode.ANY -> (pcUserAgentList + mobileUserAgentList).random()
        else -> null
    }
}

typealias MutableParameters = MutableList<Pair<String, String>>

fun getRequest(
    url: String,
    data: MutableParameters,
    apiMode: ApiMode.ApiModeEntity,
    baseUrl: String = "https://music.163.com",
    referer: String = "https://music.163.com",
    userAgentMode: UserAgentModeEntity = UserAgentMode.PC
): Request {
    FuelManager.instance.basePath = baseUrl

    val header = mutableMapOf(
        "User-Agent" to randomUserAgent(userAgentMode)!!,
        "Content-Type" to "application/x-www-form-urlencoded", //If Post
        "Referer" to referer,

        )
    if (!DataProvider.realIP.isNullOrBlank()) {
        header["X-Real-IP"] = DataProvider.realIP!!
        header["X-Forwarded-For"] = DataProvider.realIP!!
    }

    //var cookie =
    if (DataProvider.cookie.isNotEmpty()) {
        if (DataProvider.cookie.containsKey("MUSIC_U")) {
            if (DataProvider.cookie.containsKey("MUSIC_A")) {
                // TODO: 游客
            }
        }
        header["Cookie"] =
            DataProvider.cookie.map { "${URLEncodeUtil.encode(it.key)}=${URLEncodeUtil.encode(it.value)}" }
                .joinToString(";")
    }


    val param = with(DataProvider) {
        when (apiMode) {
            WE_API -> {
                data.add("csrf_token" to if (cookie.containsKey("_csrf")) cookie["_csrf"]!! else "")
                val dataMap = mutableMapOf<String, String>()
                data.map {
                    dataMap[it.first] = it.second
                }
                Crypto.weApi(Json.encodeToString(dataMap))
            }

            E_API -> {
                val dataMap = mutableMapOf<String, String>()
                data.map {
                    dataMap[it.first] = it.second
                }
                val jsonObject = JSONObject.parseObject(JSON.toJSONString(dataMap))
                val subHeader = mapOf(
                    "osver" to if (cookie.containsKey("osver")) cookie["osver"] else "",
                    "deviceId" to if (cookie.containsKey("deviceId")) cookie["deviceId"] else "",
                    "appver" to if (cookie.containsKey("appver")) cookie["appver"] else "8.7.01",
                    "versioncode" to if (cookie.containsKey("versioncode")) cookie["versioncode"] else "140", //版本号
                    "mobilename" to if (cookie.containsKey("mobilename")) cookie["mobilename"] else "", //设备model
                    "buildver" to LocalDateTime.now().toString().substring(0, 10),
                    "resolution" to "1920x1080", //设备分辨率
                    "__csrf" to if (cookie.containsKey("__csrf")) cookie["_csrf"] else "",
                    "os" to "android",
                    "channel" to if (cookie.containsKey("channel")) cookie["channel"] else "",
                    "requestId" to "${LocalDateTime.now()}_${floor(Math.random() * 1000).toString().padStart(4, '0')}",
                )

                if (cookie.containsKey("MUSIC_U")) header["MUSIC_U"] = cookie["MUSIC_U"]!!
                if (cookie.containsKey("MUSIC_A")) header["MUSIC_A"] = cookie["MUSIC_A"]!!
                header["Cookie"] = subHeader.map { "${URLEncodeUtil.encode(it.key)}=${URLEncodeUtil.encode(it.value)}" }
                    .joinToString(";")
                jsonObject["header"] = subHeader
                Crypto.eApi(url.replace("/eapi", "/api"), jsonObject.toJSONString())
            }

            LINUX_API -> Crypto.linuxApi(Json.encodeToString(data))
            else -> throw RuntimeException("Illegal Api Mode")
        }
    }
    return Fuel.post(url, param).header(header)
}







