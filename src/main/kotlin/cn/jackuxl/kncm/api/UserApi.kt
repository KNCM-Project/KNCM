package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.Request

object UserApi {
    fun getDetail(id: Long): Request {
        val params = mutableListOf<Pair<String, String>>()
        return getRequest(
            url = "/weapi/v1/user/detail/${id}",
            data = params,
            apiMode = ApiMode.WE_API
        )
    }
}