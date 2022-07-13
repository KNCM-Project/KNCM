package cn.jackuxl.ncm.util

import org.junit.jupiter.api.Test

internal class CryptoTest {

    @Test
    fun linuxApi() {
        println(Crypto.linuxApi("[1]"))
    }

    @Test
    fun eApi() {
        println(Crypto.eApi("https://music.163.com/", "[1]"))
    }

//    @Test
//    fun weApi(){
//        println(Crypto.weApi(""))
//    }

}