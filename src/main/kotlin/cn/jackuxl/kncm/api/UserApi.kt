package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.UrlParamPair
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class UserApi {
    fun getDetail(id:Int): Request {
        val params = UrlParamPair<String>()
        return getRequest(
            url = "/weapi/v1/user/detail/${id}",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }
}