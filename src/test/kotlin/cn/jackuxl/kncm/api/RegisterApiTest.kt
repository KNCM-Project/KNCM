package cn.jackuxl.kncm.api

import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class RegisterApiTest {

    @Test
    fun registerByCellphone() {
                runBlocking {
                    RegisterApi.registerByCellphone("JackuXL", "18000000000", "password", 2333)
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
            RegisterApi.checkNickName("JackuXL").responseString { _, _, result ->
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
            RegisterApi.replaceCellPhone("JackuXL", 2333, 2333).responseString { _, _, result ->
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
            RegisterApi.initNickName("JackuXL").responseString { request, response, result ->
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
            RegisterApi.checkPhoneExistence("18000000000").responseString { request, response, result ->
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