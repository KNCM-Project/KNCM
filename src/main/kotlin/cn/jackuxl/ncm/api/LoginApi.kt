package cn.jackuxl.ncm.api

import cn.hutool.crypto.SecureUtil
import cn.jackuxl.ncm.entity.UrlParamPair
import cn.jackuxl.ncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class LoginApi(val user:User) {
    fun loginByCellphone(): Request {
        val params = UrlParamPair<String>().params(
            "phone" to user.account,
            "countrycode" to user.countrycode.toString(),
            "rememberLogin" to "true",
        )

        if(user.captcha.isNullOrBlank()){
            params.param("password",if(user.md5) user.password else SecureUtil.md5(user.password))
        }
        else{
            params.param("captcha", user.captcha!!)
        }

        return getRequest(
            url = "/weapi/login/cellphone",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }


    fun loginByEmail(): Request {
        val params = UrlParamPair<String>().params(
            "username" to user.account,
            "password" to if(user.md5) user.password else SecureUtil.md5(user.password),
            "rememberLogin" to "true",
        )

        return getRequest(
            url = "/weapi/login",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }


    fun refresh(): Request {
        val params = UrlParamPair<String>()
        return getRequest(
            url = "/weapi/login/token/refresh",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun logout(): Request {
        val params = UrlParamPair<String>()
        return getRequest(
            url = "/weapi/logout",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }

    fun getStatus(): Request {
        val params = UrlParamPair<String>()
        return getRequest(
            url = "/weapi/w/nuser/account/get",
            data = params,
            referrer = "${FuelManager.instance.basePath}"
        )
    }



    // TODO: 游客登陆
    data class User(
        var account:String, // 邮箱或手机号
        var password:String, // 密码
        var captcha:String? = null,
        var md5:Boolean = false, // 密码是否经过MD5加密
        var countrycode:Int = 86 // 国家码，用于国外手机号登录，例如美国传入：1
    )
}