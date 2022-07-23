package cn.jackuxl.kncm.api

import cn.hutool.crypto.SecureUtil
import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.Request

class RegisterApi {
    fun registerByCellphone(
        nickname: String, // 用户名
        phone: String, // 手机号
        password: String, // 密码
        captcha: Int, // 验证码
        md5: Boolean = false, // 密码是否经过MD5加密
        countrycode: Int = 86, // 国家码，用于国外手机号登录，例如美国传入：1
    ): Request {
        val params = mutableListOf(
            "phone" to phone,
            "countrycode" to countrycode.toString(),
            "nickname" to nickname,
            "password" to if (md5) password else SecureUtil.md5(password),
            "captcha" to captcha.toString()
        )
        return getRequest(
            url = "/weapi/register/cellphone",
            data = params,
            apiMode = ApiMode.WE_API
        )
    }

    fun checkNickName(nickname: String): Request {
        val params = mutableListOf("nickname" to nickname)
        return getRequest(
            url = "/weapi/nickname/duplicated",
            data = params,
            apiMode = ApiMode.WE_API
        )
    }

    fun replaceCellPhone(
        phone: String, // 新手机号
        oldcaptcha: Int, // 旧手机验证码
        captcha: Int, // 新手机验证码
        countrycode: Int = 86 // 国家码，用于国外手机号登录，例如美国传入：1
    ): Request {
        val params = mutableListOf(
            "captcha" to captcha.toString(),
            "oldcaptcha" to oldcaptcha.toString(),
            "phone" to phone,
            "ctcode" to countrycode.toString()
        )
        return getRequest(
            url = "/weapi/user/replaceCellphone",
            data = params,
            apiMode = ApiMode.WE_API
        )
    }

    fun initNickName(nickname: String): Request {
        val params = mutableListOf("nickname" to nickname)
        return getRequest(
            url = "/eapi/activate/initProfile",
            data = params,
            apiMode = ApiMode.E_API
        )
    }

    fun checkPhoneExistence(phone: String, countrycode: Int = 86): Request {
        val params = mutableListOf(
            "cellphone" to phone,
            "countrycode" to countrycode.toString(),
        )
        return getRequest(
            url = "/eapi/cellphone/existence/check",
            data = params,
            apiMode = ApiMode.E_API
        )
    }
}