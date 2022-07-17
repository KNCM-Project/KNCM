package cn.jackuxl.kncm.api

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class SearchApiTest {
    private val searchApi = SearchApi()

    @Test
    fun search() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            searchApi.search("海阔天空").responseString { _, _, result ->
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
    fun searchVoice() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            searchApi.search("海阔天空", 2000).responseString { _, _, result ->
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
    fun cloudsearch() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            searchApi.cloudsearch("海阔天空").responseString { _, _, result ->
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