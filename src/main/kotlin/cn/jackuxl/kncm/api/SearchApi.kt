package cn.jackuxl.kncm.api

import cn.jackuxl.kncm.entity.ApiMode
import cn.jackuxl.kncm.getRequest
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request

class SearchApi {
    fun search(
        keyword: String, // 关键词
        type: Int = 1, // 搜索类型， 1: 单曲, 10: 专辑, 100: 歌手, 1000: 歌单, 1002: 用户, 1004: MV, 1006: 歌词, 1009: 电台, 1014: 视频, 1018:综合, 2000:声音(搜索声音返回字段格式会不一样)
        limit: Int = 30, // 返回数量
        offset: Int = 0 // 偏移数量，用于分页 , 如 : 如 :( 页数 -1)*30, 其中 30 为 limit 的值
    ): Request {
        if (type == 2000) {
            val params = mapOf(
                "keyword" to keyword,
                "scene" to "normal",
                "limit" to limit.toString(),
                "offset" to offset.toString(),
            )
            return getRequest(
                url = "/weapi/search/voice/get",
                data = params,
                mode = ApiMode.WE_API,
                referrer = "${FuelManager.instance.basePath}"
            )
        }
        val params = mapOf(
            "s" to keyword,
            "type" to type.toString(),
            "limit" to limit.toString(),
            "offset" to offset.toString(),
        )
        return getRequest(
            url = "/weapi/search/get",
            data = params,
            mode = ApiMode.WE_API,
            referrer = "${FuelManager.instance.basePath}"
        )

    }

    fun cloudsearch(
        keyword: String, // 关键词
        type: Int = 1, // 搜索类型， 1: 单曲, 10: 专辑, 100: 歌手, 1000: 歌单, 1002: 用户, 1004: MV, 1006: 歌词, 1009: 电台, 1014: 视频, 1018:综合, 2000:声音(搜索声音返回字段格式会不一样)
        limit: Int = 30, // 返回数量
        offset: Int = 0 // 偏移数量，用于分页 , 如 : 如 :( 页数 -1)*30, 其中 30 为 limit 的值
    ): Request {
        val params = mapOf(
            "s" to keyword,
            "type" to type.toString(),
            "limit" to limit.toString(),
            "offset" to offset.toString(),
            "total" to true.toString()
        )
        return getRequest(
            url = "/weapi/cloudsearch/pc",
            data = params,
            mode = ApiMode.WE_API,
            referrer = "${FuelManager.instance.basePath}"
        )
    }
}