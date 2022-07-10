package cn.jackuxl.ncm.api

import cn.jackuxl.ncm.entity.UrlParamPair
import cn.jackuxl.ncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class CaptchaApi(val phone:String,val ctcode:Int=86) {
    fun send(): Request {
        val params = UrlParamPair<String>().params(
            "cellphone" to phone,
            "ctcode" to ctcode.toString(),
        )

        return getRequest(
            url = "/weapi/sms/captcha/sent",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun verify(captcha:String): Request {
        val params = UrlParamPair<String>().params(
            "cellphone" to phone,
            "ctcode" to ctcode.toString(),
            "captcha" to captcha
        )

        return getRequest(
            url = "/weapi/sms/captcha/verify",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }
}