package cn.jackuxl.kncm.api

import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class SearchApiTest {


    @Test
    fun search() {
                runBlocking {
                    SearchApi.search("海阔天空").responseString { _, _, result ->
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
        runBlocking {
            SearchApi.search("海阔天空", 2000).responseString { _, _, result ->
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
        runBlocking {
            SearchApi.cloudsearch("海阔天空").responseString { _, _, result ->
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