package cn.jackuxl.ncm.api

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class SearchApiTest {
    private val searchApi = SearchApi()

    @Test
    fun search() {
        FuelManager.instance.basePath = "https://music.163.com"
        searchApi.search("海阔天空").responseString { request, response, result ->
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
    fun searchVoice() {
        FuelManager.instance.basePath = "https://music.163.com"
        searchApi.search("海阔天空", 2000).responseString { request, response, result ->
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
    fun cloudsearch() {
        FuelManager.instance.basePath = "https://music.163.com"
        searchApi.cloudsearch("海阔天空").responseString { request, response, result ->
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