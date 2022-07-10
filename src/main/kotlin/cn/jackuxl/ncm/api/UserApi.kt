package cn.jackuxl.ncm.api

import cn.hutool.crypto.SecureUtil
import cn.jackuxl.ncm.entity.UrlParamPair
import cn.jackuxl.ncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class UserApi(val user: User) {


    fun loginByCellphone(): Request {
        val params = UrlParamPair<String>().params(
            "phone" to user.account,
            "countrycode" to user.countrycode.toString(),
            "password" to if(user.md5) user.password else SecureUtil.md5(user.password),
            "rememberLogin" to "true",
        )

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

    // TODO: 支持验证码
    data class User(
        var account:String, // 邮箱或手机号
        var password:String, // 密码
        var countrycode:Int = 86, // 国家码，用于国外手机号登录，例如美国传入：1
        var md5:Boolean = false // 密码是否经过MD5加密
    )
}