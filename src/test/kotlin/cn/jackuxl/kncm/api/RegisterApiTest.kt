package cn.jackuxl.kncm.api

import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class RegisterApiTest {
    val registerApi = RegisterApi()

    @Test
    fun registerByCellphone() {
                runBlocking {
                    registerApi.registerByCellphone("JackuXL", "18000000000", "password", 2333)
                        .responseString { _, _, result ->
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
    fun checkNickName() {
        runBlocking {
            registerApi.checkNickName("JackuXL").responseString { _, _, result ->
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
    fun replaceCellPhone() {
        runBlocking {
            registerApi.replaceCellPhone("JackuXL", 2333, 2333).responseString { _, _, result ->
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
    fun initNickName() {
        runBlocking {
            registerApi.initNickName("JackuXL").responseString { request, response, result ->
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
    fun checkPhoneExistence() {
        runBlocking {
            registerApi.checkPhoneExistence("18000000000").responseString { request, response, result ->
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