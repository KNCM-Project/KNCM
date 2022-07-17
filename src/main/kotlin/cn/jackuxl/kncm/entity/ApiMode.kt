package cn.jackuxl.kncm.entity

object ApiMode {
    val WE_API = ApiModeEntity(0)
    val E_API = ApiModeEntity(1)
    val LINUX_API = ApiModeEntity(2)

    data class ApiModeEntity(var id: Int)
}