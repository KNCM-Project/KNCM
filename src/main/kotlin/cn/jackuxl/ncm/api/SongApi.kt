package cn.jackuxl.ncm.api

import cn.jackuxl.ncm.entity.UrlParamPair
import cn.jackuxl.ncm.getRequest
import cn.jackuxl.ncm.util.JSSecret
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SongApi(val song: Song) {
    private fun getCommentsParam(offset: Int): UrlParamPair<String> {
        return UrlParamPair<String>().params(
            "username" to "",
            "password" to "",
            "rememberLogin" to "true",
            "offset" to offset.toString()
        )
    }
//    private fun getDetailParam(): UrlParamPair<List<Map<String,Int>>> {
//        return UrlParamPair<List<Map<String,Int>>>().params(
//            "c" to listOf(mapOf("id" to song.id.toInt())),
//        )
//    }
    private fun getDetailParam(): UrlParamPair<String> {
        return UrlParamPair<String>().params(
            "c" to Json.encodeToString(listOf(mapOf("id" to song.id.toInt()))),
        )
    }
    fun getComments(offset: Int): Request {
        return getRequest(
            url = "/weapi/v1/resource/comments/R_SO_4_${song.id}",
            data = JSSecret.transData(Json.encodeToString(getCommentsParam(offset).params)),
            referrer = "${FuelManager.instance.basePath}/song?id=${song.id}"
        )
    }

    fun getDetail(): Request {
        return getRequest(
            url = "/api/v3/song/detail",
            data = JSSecret.transData(Json.encodeToString(getDetailParam().params)),
            referrer = "${FuelManager.instance.basePath}/song?id=${song.id}"
        )
    }

    data class Song(var id:String)
}