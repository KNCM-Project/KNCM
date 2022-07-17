package cn.jackuxl.kncm.api

import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class SearchApiTest {
    private val searchApi = SearchApi()

    @Test
    fun search() {
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