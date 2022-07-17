package cn.jackuxl.kncm.api

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class RegisterApiTest {
    val registerApi = RegisterApi()

    @Test
    fun registerByCellphone() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            registerApi.registerByCellphone("JackuXL", "18000000000", "password", 2333).responseString { _, _, result ->
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
        FuelManager.instance.basePath = "https://music.163.com"
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
        FuelManager.instance.basePath = "https://music.163.com"
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
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            registerApi.initNickName("JackuXL").responseString { request, response, result ->
                println(request)
                println(response)
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