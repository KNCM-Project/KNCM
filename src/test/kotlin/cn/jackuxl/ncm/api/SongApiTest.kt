package cn.jackuxl.ncm.api

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import org.junit.jupiter.api.Test
import cn.jackuxl.ncm.api.SongApi.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*

internal class SongApiTest {
    private val songApi = SongApi()
    private val id = 1960903012L
    @Test
    fun getComments() {
        FuelManager.instance.basePath = "https://music.163.com"
        songApi.getComments(id,2).responseString { request, response, result ->
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
    fun getDetail() {
        FuelManager.instance.basePath = "https://music.163.com"
        songApi.getDetail(id).responseString { request, response, result ->
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
            delay(10000000L)  //阻塞主线程防止过快退出
        }
    }
}