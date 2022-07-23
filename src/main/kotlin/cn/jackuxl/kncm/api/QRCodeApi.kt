package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.Request

object QRCodeApi {
    fun getKey(): Request {
        val params = mutableListOf(
            "type" to "1"
        )

        return getRequest(
            url = "/weapi/login/qrcode/unikey",
            data = params,
            apiMode = ApiMode.WE_API
        )
    }

    fun createQRCode(key:String): String {
        return "https://music.163.com/login?codekey=$key"
    }

    fun checkStatus(key:String): Request {
        val params = mutableListOf(
            "key" to key,
            "type" to "1"
        )
        return getRequest(
            url = "/weapi/login/qrcode/client/login",
            data = params,
            apiMode = ApiMode.WE_API
        )
    }
}