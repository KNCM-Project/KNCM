package cn.jackuxl.ncm.api

import cn.hutool.crypto.SecureUtil
import cn.jackuxl.ncm.entity.UrlParamPair
import cn.jackuxl.ncm.getRequest
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