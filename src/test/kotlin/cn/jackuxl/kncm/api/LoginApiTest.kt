package cn.jackuxl.kncm.api

import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class LoginApiTest {
    private val userApi = LoginApi()

    @Test
    fun loginByCellphone() {
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