package cn.jackuxl.kncm.api

import cn.hutool.crypto.SecureUtil
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class LoginApi {
    fun loginByCellphone(
        phone: String, // 手机号
        password: String, // 密码
        md5: Boolean = false, // 密码是否经过MD5加密
        countrycode: Int = 86 // 国家码，用于国外手机号登录，例如美国传入：1
    ): Request {
        val params = mapOf(
            "phone" to phone,
            "countrycode" to countrycode.toString(),
            "rememberLogin" to "true",
            "password" to if (md5) password else SecureUtil.md5(password)
        )
        return getRequest(
            url = "/weapi/login/cellphone",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }
    fun loginByCellphone(
        phone: String, // 手机号
        captcha: Int, // 验证码
        countrycode: Int = 86 // 国家码，用于国外手机号登录，例如美国传入：1
    ): Request {
        val params = mapOf(
            "phone" to phone,
            "countrycode" to countrycode.toString(),
            "rememberLogin" to "true",
            "captcha" to captcha.toString()
        )
        return getRequest(
            url = "/weapi/login/cellphone",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun loginByEmail(
        email: String, // 邮箱
        password: String, // 密码
        md5: Boolean = false, // 密码是否经过MD5加密
    ): Request {
        val params = mapOf(
            "username" to email,
            "password" to if (md5) password else SecureUtil.md5(password),
            "rememberLogin" to "true",
        )

        return getRequest(
            url = "/weapi/login",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }


    fun refresh(): Request {
        val params = mapOf<String, String>()
        return getRequest(
            url = "/weapi/login/token/refresh",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun logout(): Request {
        val params = mapOf<String, String>()
        return getRequest(
            url = "/weapi/logout",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun getStatus(): Request {
        val params = mapOf<String, String>()
        return getRequest(
            url = "/weapi/w/nuser/account/get",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun getAccount(): Request {
        val params = mapOf<String, String>()
        return getRequest(
            url = "/weapi/nuser/account/get",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }


    // TODO: 游客登录
}