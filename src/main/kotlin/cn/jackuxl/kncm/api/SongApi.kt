package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.Request
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SongApi {
    fun getComments(id:Long, offset: Int): Request {
        val param = mapOf(
            "username" to "",
            "password" to "",
            "rememberLogin" to "true",
            "offset" to offset.toString()
        )
        return getRequest(
            url = "/weapi/v1/resource/comments/R_SO_4_${id}",
            data = param,
            mode = ApiMode.WE_API
        )
    }

    fun getDetail(id: Long): Request {
        val param = mapOf(
            "c" to Json.encodeToString(listOf(mapOf("id" to id))),
        )
        return getRequest(
            url = "/weapi/v3/song/detail",
            data = param,
            mode = ApiMode.WE_API
        )
    }

    fun getUrl(id: Long, br: String = "999000"): Request {
        val param = mapOf(
            "ids" to "[$id]",
            "br" to br,
        )
        return getRequest(
            url = "/eapi/song/enhance/player/url",
            data = param,
            mode = ApiMode.E_API,
            baseUrl = "https://interface3.music.163.com"
        )
    }

//    fun isAvailable(id:Long): Request {
//        val param = mapOf(
//            "ids" to "[$id]",
//            "br" to br,
//        )
//        return getRequest(
//            url = "/eapi/song/enhance/player/url",
//            data = param,
//            mode = ApiMode.E_API,
//            baseUrl = "https://interface3.music.163.com"
//        )
//    }
}