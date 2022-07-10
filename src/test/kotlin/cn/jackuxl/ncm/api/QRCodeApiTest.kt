package cn.jackuxl.ncm.api

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class QRCodeApiTest {
    val qrCodeApi:QRCodeApi = QRCodeApi()
    @Test
    fun getKey() {
        FuelManager.instance.basePath = "https://music.163.com"
        qrCodeApi.getKey().responseString { request, response, result ->
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
    fun createQRCode() {
        FuelManager.instance.basePath = "https://music.163.com"
        println(qrCodeApi.createQRCode("c011d352-d445-4e04-8fb3-f7cd8f7f3b01"))
    }

    @Test
    fun checkStatus() {
        FuelManager.instance.basePath = "https://music.163.com"
        qrCodeApi.checkStatus("c011d352-d445-4e04-8fb3-f7cd8f7f3b01").responseString { request, response, result ->
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