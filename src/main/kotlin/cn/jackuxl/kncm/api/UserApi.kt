package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.Request

class UserApi {
    fun getDetail(id: Long): Request {
        val params = mapOf<String, String>()
        return getRequest(
            url = "/weapi/v1/user/detail/${id}",
            data = params,
            mode = ApiMode.WE_API
        )
    }
}