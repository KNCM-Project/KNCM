package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
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

}