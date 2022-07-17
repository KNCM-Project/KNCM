package cn.jackuxl.kncm.api

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class CaptchaApiTest {
    private val captchaApi = CaptchaApi("18000000000")
    @Test
    fun send() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            captchaApi.send().responseString { _, _, result ->
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
    fun verify() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            captchaApi.verify("2333").responseString { _, _, result ->
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