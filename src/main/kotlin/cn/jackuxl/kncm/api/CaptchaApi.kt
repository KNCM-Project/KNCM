package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class CaptchaApi(val phone:String,val ctcode:Int=86) {
    fun send(): Request {
        val params = mapOf(
            "cellphone" to phone,
            "ctcode" to ctcode.toString(),
        )

        return getRequest(
            url = "/weapi/sms/captcha/sent",
            data = params,
            mode = ApiMode.WE_API,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun verify(captcha:String): Request {
        val params = mapOf(
            "cellphone" to phone,
            "ctcode" to ctcode.toString(),
            "captcha" to captcha
        )

        return getRequest(
            url = "/weapi/sms/captcha/verify",
            data = params,
            mode = ApiMode.WE_API,
            referrer = "${FuelManager.instance.basePath}"
        )
    }
}