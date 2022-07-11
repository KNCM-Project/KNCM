package cn.jackuxl.ncm.api

import cn.jackuxl.ncm.entity.UrlParamPair
import cn.jackuxl.ncm.getRequest
import cn.jackuxl.ncm.util.JSSecret
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SongApi {
    fun getComments(id:Long, offset: Int): Request {
        val param = UrlParamPair<String>().params(
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

    fun getDetail(id:Long): Request {
        val param = UrlParamPair<String>().params(
            "c" to Json.encodeToString(listOf(mapOf("id" to id))),
        )
        return getRequest(
            url = "/weapi/v3/song/detail",
            data = param,
            referrer = "${FuelManager.instance.basePath}/song?id=${id}"
        )
    }
}