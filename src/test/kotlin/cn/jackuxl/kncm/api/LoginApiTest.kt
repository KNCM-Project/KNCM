package cn.jackuxl.kncm.api

import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class LoginApiTest {
    @Test
    fun loginByCellphone() {
                runBlocking {
                    LoginApi.loginByCellphone("18000000000", "password").responseString { _, _, result ->
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
            LoginApi.loginByCellphone("18000000000", 2333).responseString { _, _, result ->
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
            LoginApi.loginByEmail("admin@163.com", "password").responseString { _, _, result ->
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
            LoginApi.refresh().responseString { _, _, result ->
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
            LoginApi.logout().responseString { _, _, result ->
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
            LoginApi.getStatus().responseString { _, _, result ->
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
            LoginApi.getAccount().responseString { _, _, result ->
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