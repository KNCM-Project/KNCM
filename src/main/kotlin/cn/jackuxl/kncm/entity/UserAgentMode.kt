package cn.jackuxl.kncm.entity

object UserAgentMode {
    val PC = UserAgentModeEntity(0)
    val MOBILE = UserAgentModeEntity(1)
    val ANY = UserAgentModeEntity(3)

    data class UserAgentModeEntity(var id: Int)
}