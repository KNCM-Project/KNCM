package cn.jackuxl.kncm.extension

import cn.jackuxl.kncm.extension.CookieExtension.toCookieList
import cn.jackuxl.kncm.extension.CookieExtension.toCookieString
import org.junit.jupiter.api.Test

internal class CookieExtensionTest {
    private val cookie =
        "traceid=ca4c9c8b50; _ga=GA1.2.704388932.1617513691; _gid=GA1.2.85700497.1617513691; _qpsvr_localtk=0.2810983015733952; RK=uCLoGvbeXG; ptcz=f997dfbd9d785b321bb377710933cae37308f34abb4b2340706eda53e23455cc; pgv_pvid=4861902100; pgv_info=ssid=s7567138318; ptui_loginuin=327844761; qqmusic_uin=0327844761; qqmusic_fromtag=6; qqmusic_key=@R3lRxCHCr; p_uin=o0327844761; _gat_gtag_UA_172919674_1=1; uin=o0327844761; skey=@APPCpl4ip; pt4_token=EWPb3WyZfLoQPIfmYfSjWDKOJXOIt09*xjIidJVdC54_; p_skey=6dlS-INlRzNbnEOA09*Zn2l6cfPMI4ardDbcWCwqsQI_"
    private val simpleCookie = "a=1"

    @Test
    fun toCookieString() {
        println(cookie.toCookieList().toCookieString())
        println(simpleCookie.toCookieList().toCookieString())
    }
}