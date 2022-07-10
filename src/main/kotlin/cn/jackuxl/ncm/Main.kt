package cn.jackuxl.ncm

import cn.jackuxl.ncm.api.SongApi
import cn.jackuxl.ncm.api.SongApi.*

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun main() {
    FuelManager.instance.basePath = "https://music.163.com"
    val songId = "28754105"
    SongApi(Song(songId)).getComments(2).responseString { request, response, result ->
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                println(ex)
            }
            is Result.Success -> {
                val data = result.get()
                println(data)
            }
        }
    }
    runBlocking {     // 这个表达式阻塞了主线程
        delay(3000L)  //阻塞主线程防止过快退出
    }

}



