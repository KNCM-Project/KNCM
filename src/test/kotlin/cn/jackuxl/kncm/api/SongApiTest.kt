package cn.jackuxl.kncm.api

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class SongApiTest {
    private val songApi = SongApi()
    private val id = 1960903012L
    @Test
    fun getComments() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            songApi.getComments(id, 2).responseString { _, _, result ->
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
    fun getDetail() {
        FuelManager.instance.basePath = "https://music.163.com"
        runBlocking {
            songApi.getDetail(id).responseString { _, _, result ->
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