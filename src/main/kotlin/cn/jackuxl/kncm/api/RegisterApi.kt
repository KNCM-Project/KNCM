package cn.jackuxl.kncm.api

import cn.hutool.crypto.SecureUtil
import cn.jackuxl.kncm.entity.UrlParamPair
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class RegisterApi {
    fun registerByCellphone(
        nickname: String, // 用户名
        phone: String, // 手机号
        password: String, // 密码
        countrycode: Int = 86, // 国家码，用于国外手机号登录，例如美国传入：1
        captcha: Int, // 验证码
        md5: Boolean = false // 密码是否经过MD5加密
    ): Request {
        val params = UrlParamPair<String>().params(
            "phone" to phone,
            "countrycode" to countrycode.toString(),
            "nickname" to nickname,
            "password" to if (md5) password else SecureUtil.md5(password),
            "captcha" to captcha.toString()
        )
        return getRequest(
            url = "/weapi/register/cellphone",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun checkNickName(nickname: String): Request {
        val params = UrlParamPair<String>().param("nickname", nickname)
        return getRequest(
            url = "/weapi/nickname/duplicated",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun replaceCellPhone(
        phone: String, // 新手机号
        oldcaptcha: String, // 旧手机验证码
        captcha: String, // 新手机验证码
        countrycode: Int = 86 // 国家码，用于国外手机号登录，例如美国传入：1
    ): Request {
        val params = UrlParamPair<String>().params(
            "captcha" to captcha,
            "oldcaptcha" to oldcaptcha,
            "phone" to phone,
            "ctcode" to countrycode.toString()
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
}