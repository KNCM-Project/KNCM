package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class QRCodeApi {
    fun getKey(): Request {
        val params = mapOf(
            "type" to 1
        )

        return getRequest(
            url = "/weapi/login/qrcode/unikey",
            data = params,
            mode = ApiMode.WE_API,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun createQRCode(key:String): String {
        return "${FuelManager.instance.basePath}/login?codekey=$key"
    }

    fun checkStatus(key:String): Request {
        val params = mapOf(
            "key" to key,
            "type" to "1"
        )
        return getRequest(
            url = "/weapi/login/qrcode/client/login",
            data = params,
            mode = ApiMode.WE_API,
            referrer = "${FuelManager.instance.basePath}"
        )
    }
}