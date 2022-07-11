package cn.jackuxl.ncm.api

import org.junit.jupiter.api.Test
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

internal class LoginApiTest {
    private val userApi = LoginApi()

    @Test
    fun loginByCellphone() {
        FuelManager.instance.basePath = "https://music.163.com"
        userApi.loginByCellphone("18000000000","password").responseString { request, response, result ->
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
    fun loginByCaptcha() {
        FuelManager.instance.basePath = "https://music.163.com"
        userApi.loginByCellphone("18000000000",2333).responseString { request, response, result ->
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
        userApi.loginByEmail("admin@163.com","password").responseString { request, response, result ->
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
    fun refresh() {
        FuelManager.instance.basePath = "https://music.163.com"
        userApi.refresh().responseString { request, response, result ->
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
    fun logout() {
        FuelManager.instance.basePath = "https://music.163.com"
        userApi.logout().responseString { request, response, result ->
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
    fun getStatus() {
        FuelManager.instance.basePath = "https://music.163.com"
        userApi.getStatus().responseString { request, response, result ->
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
    fun getAccount() {
        FuelManager.instance.basePath = "https://music.163.com"
        userApi.getAccount().responseString { request, response, result ->
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