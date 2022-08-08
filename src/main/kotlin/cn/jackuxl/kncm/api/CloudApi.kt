package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
import com.alibaba.fastjson.JSONArray
import com.github.kittinunf.fuel.core.Request

object CloudApi {
    @LoginRequired
    fun list(
        limit: Int = 30,
        offset: Int = 0
    ): Request { //TODO:https://github.com/Binaryify/NeteaseCloudMusicApi/issues/1579
        val params = mutableListOf(
            "limit" to limit.toString(),
            "offset" to offset.toString(),
        )

        return getRequest(
            url = "/weapi/v1/cloud/get",
            data = params,
            apiMode = ApiMode.WE_API
        )
    }

    @LoginRequired
    fun getDetail(id: Long): Request {
        return getDetail(longArrayOf(id))
    }

    @LoginRequired
    fun getDetail(id: LongArray): Request {
        val params = mutableListOf(
            "songIds" to JSONArray.toJSONString(id),
        )

        return getRequest(
            url = "/weapi/v1/cloud/get/byids",
            data = params,
            apiMode = ApiMode.WE_API
        )
    }

    @LoginRequired
    fun delete(id: Long): Request {
        return delete(longArrayOf(id))
    }

    @LoginRequired
    fun delete(id: LongArray): Request {
        val params = mutableListOf(
            "songIds" to JSONArray.toJSONString(id),
        )

        return getRequest(
            url = "/weapi/cloud/del",
            data = params,
            apiMode = ApiMode.WE_API
        )
    }

}