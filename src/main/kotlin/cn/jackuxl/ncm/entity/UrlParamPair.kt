package cn.jackuxl.ncm.entity
import kotlinx.serialization.Serializable

@Serializable
data class UrlParamPair <T>(var params: MutableMap<String,T> = mutableMapOf()){
    fun param(key: String, value: T): UrlParamPair<T> {
        params[key] = value
        return this
    }
    fun params(vararg params: Pair<String,T>):UrlParamPair<T>{
        this.params = mutableMapOf(*params)
        return this
    }
}