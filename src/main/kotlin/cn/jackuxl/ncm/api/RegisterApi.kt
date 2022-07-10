package cn.jackuxl.ncm.api

import cn.hutool.crypto.SecureUtil
import cn.jackuxl.ncm.entity.UrlParamPair
import cn.jackuxl.ncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class RegisterApi() {
    fun registerByCellphone(registerData: RegisterData):Request {
        val params = UrlParamPair<String>().params(
            "phone" to registerData.phone,
            "countrycode" to registerData.countrycode.toString(),
            "nickname" to registerData.nickname,
            "password" to if(registerData.md5) registerData.password else SecureUtil.md5(registerData.password),
            "captcha" to registerData.captcha
        )
        return getRequest(
            url = "/weapi/register/cellphone",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }
    fun checkNickName(nickname: String):Request {
        val params = UrlParamPair<String>().param("nickname",nickname)
        return getRequest(
            url = "/weapi/nickname/duplicated",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }
    fun replaceCellPhone(replaceData: ReplaceData):Request {
        val params = UrlParamPair<String>().params(
            "captcha" to replaceData.captcha,
            "oldcaptcha" to replaceData.oldcaptcha,
            "phone" to replaceData.phone,
            "ctcode" to replaceData.countrycode.toString()
        )
        return getRequest(
            url = "/weapi/user/replaceCellphone",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    // TODO: 初始化昵称（eapi）
//    fun initNickName(nickname: String):Request {
//        val params = UrlParamPair<String>().param("nickname",nickname)
//        return getRequest(
//            url = "/weapi/nickname/duplicated",
//            data = params,
//            referrer = "${FuelManager.instance.basePath}"
//        )
//    }

    // TODO: 检测注册（eapi）
//    fun isRegistered(phone:String,countrycode: Int=86):Request{
//        val params = UrlParamPair<String>().params(
//            "phone" to phone,
//            "countrycode" to countrycode.toString(),
//        )
//        return getRequest(
//            url = "/weapi/register/cellphone",
//            data = params,
//            referrer = "${FuelManager.instance.basePath}"
//        )
//    }

    data class RegisterData(
        var nickname:String, // 用户名
        var phone:String, // 手机号
        var password:String, // 密码
        var countrycode:Int = 86, // 国家码，用于国外手机号登录，例如美国传入：1
        var captcha:String,
        var md5:Boolean = false // 密码是否经过MD5加密
    )

    data class ReplaceData(
        var phone:String, // 新手机号
        var oldcaptcha:String,
        var captcha:String,
        var countrycode:Int = 86 // 国家码，用于国外手机号登录，例如美国传入：1
    )
}