package cn.jackuxl.kncm.api

import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class CloudApiTest {

    @Test
    fun list() {
        runBlocking {
            CloudApi.list().responseString { _, _, result ->
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
        runBlocking {
            CloudApi.getDetail(longArrayOf(1879351592L, 1872051726L)).responseString { _, _, result ->
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
    fun delete() {
        runBlocking {
            CloudApi.delete(longArrayOf(1872051726L)).responseString { _, _, result ->
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