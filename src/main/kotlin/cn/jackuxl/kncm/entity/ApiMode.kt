package cn.jackuxl.kncm.entity

object ApiMode {
    val WE_API = ApiModeEntity(0)
    val E_API = ApiModeEntity(1)

    @Deprecated("Please use EApi(Recommended)/WeApi.")
    val LINUX_API = ApiModeEntity(2)

    data class ApiModeEntity(var id: Int)
}

object UserAgentMode {
    val PC = UserAgentModeEntity(0)
    val MOBILE = UserAgentModeEntity(1)
    val ANY = UserAgentModeEntity(3)

    data class UserAgentModeEntity(var id: Int)
}