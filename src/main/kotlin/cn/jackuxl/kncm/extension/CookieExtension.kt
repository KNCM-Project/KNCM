package cn.jackuxl.kncm.extension

import cn.hutool.core.net.URLEncodeUtil
import java.net.HttpCookie

typealias CookieList = List<HttpCookie>
typealias MutableCookieList = MutableList<HttpCookie>

object CookieExtension {
    fun String.toCookieList(): CookieList {
        val list: MutableCookieList = mutableListOf()
        val value = replace(" ", "")
        if (value.contains(";")) {
            val values = value.split(";")
            values.forEach {
                if (it.contains("=")) {
                    val pair = it.split("=")
                    list.add(HttpCookie(pair[0], pair[1]))
                }
            }
        } else {
            val pair = value.split("=")
            list.add(HttpCookie(pair[0], pair[1]))
        }
        return list
    }


    fun CookieList.toCookieString(): String {
        return joinToString("; ") { "${URLEncodeUtil.encode(it.name)}=${URLEncodeUtil.encode(it.value)}" }
    }

    fun CookieList.getCookie(key: String): String? {
        forEach {
            if (it.name == key) {
                return it.value
            }
        }
        return null
    }

    fun CookieList.containsCookie(key: String): Boolean {
        forEach {
            if (it.name == key) {
                return true
            }
        }
        return false
    }
}