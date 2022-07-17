package cn.jackuxl.kncm.api

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class LoginApiTest {
    private val userApi = LoginApi()

    @Test
    fun loginByCellphone() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            userApi.loginByCellphone("18000000000", "password").responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        throw result.getException()
                    }

                    is Result.Success -> {
                        println(result.get())
                    }
                }
            }.join()
        }
    }

    @Test
    fun loginByCaptcha() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            userApi.loginByCellphone("18000000000", 2333).responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        throw result.getException()
                    }

                    is Result.Success -> {
                        println(result.get())
                    }
                }
            }.join()
        }
    }

    @Test
    fun loginByEmail() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            userApi.loginByEmail("admin@163.com", "password").responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        throw result.getException()
                    }

                    is Result.Success -> {
                        println(result.get())
                    }
                }
            }.join()
        }
    }

    @Test
    fun refresh() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            userApi.refresh().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        throw result.getException()
                    }

                    is Result.Success -> {
                        println(result.get())
                    }
                }
            }.join()
        }
    }

    @Test
    fun logout() {
        FuelManager.instance.basePath = "https://music.163.com"

        runBlocking {
            userApi.logout().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        throw result.getException()
                    }

                    is Result.Success -> {
                        println(result.get())
                    }
                }
            }.join()
        }
    }

    @Test
    fun getStatus() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            userApi.getStatus().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        throw result.getException()
                    }

                    is Result.Success -> {
                        println(result.get())
                    }
                }
            }.join()
        }
    }

    @Test
    fun getAccount() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            userApi.getAccount().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        throw result.getException()
                    }

                    is Result.Success -> {
                        println(result.get())
                    }
                }
            }.join()
        }
    }
}