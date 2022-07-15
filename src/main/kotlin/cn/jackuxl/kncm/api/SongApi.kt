package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
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
            referrer = "${FuelManager.instance.basePath}/song?id=${id}"
        )
    }

    fun getDetail(id: Long): Request {
        val param = mapOf(
            "c" to Json.encodeToString(listOf(mapOf("id" to id))),
        )
        return getRequest(
            url = "/weapi/v3/song/detail",
            data = param,
            referrer = "${FuelManager.instance.basePath}/song?id=${id}"
        )
    }

    // TODO: eapi
//    fun getUrl(id:Long,br:String): Request {
//        val param = mapOf(
//            "c" to Json.encodeToString(listOf(mapOf("id" to id))),
//        )
//        return getRequest(
//            url = "/weapi/v3/song/detail",
//            data = param,
//            referrer = "${FuelManager.instance.basePath}/song?id=${id}"
//        )
//    }
}