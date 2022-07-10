package cn.jackuxl.ncm.api

import org.junit.jupiter.api.Test
import cn.jackuxl.ncm.api.UserApi.User
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

import org.junit.jupiter.api.Assertions.*

internal class UserApiTest {
    private val userApi = UserApi(User("","12345678"))
    // Todo: Fix {"code":400,"message":"登陆失败,请进行安全验证"}
    @Test
    fun loginByCellphone() {
        FuelManager.instance.basePath = "https://music.163.com"
        userApi.user.account = "13800000000"
        userApi.loginByCellphone().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    throw result.getException()
                }
                is Result.Success -> {
                    println(result.get())
                }
            }
        }
        runBlocking {     // 这个表达式阻塞了主线程
            delay(3000L)  //阻塞主线程防止过快退出
        }
    }
    @Test
    fun loginByEmail() {
        FuelManager.instance.basePath = "https://music.163.com"
        userApi.user.account = "admin@163.com"
        userApi.loginByEmail().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    throw result.getException()
                }
                is Result.Success -> {
                    println(result.get())
                }
            }
        }
        runBlocking {     // 这个表达式阻塞了主线程
            delay(3000L)  //阻塞主线程防止过快退出
        }
    }
}