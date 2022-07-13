package cn.jackuxl.ncm.util


import cn.hutool.core.codec.Base64
import cn.hutool.core.util.HexUtil
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object Crypto {
    private val eapiKey = "e82ckenh8dichen8"
    private val linuxApiKey = "rFgB&h#%2?^eDg:Q"
    private val iv = "0102030405060708"

    private fun aesEncrypt(value: String, key: String): ByteArray {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")
        val cipherMode = Cipher.ENCRYPT_MODE
        cipher.init(cipherMode, keySpec)
        return cipher.doFinal(value.toByteArray())
    }

    private fun aesEncrypt(value: String, key: String, iv: String): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")
        val cipherMode = Cipher.ENCRYPT_MODE
        val ivSpec = IvParameterSpec(Base64.decode(iv))
        cipher.init(cipherMode, keySpec, ivSpec)
        return cipher.doFinal(value.toByteArray())
    }

//    private fun rsaEncrypt(value: String,  key: String): ByteArray {
//        StrUtil.fill(value,'0',128,true).length
//        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
//        SecureUtil.rsa()
//    }

    fun linuxApi(param: String): Map<String, String> {
        return mapOf(
            "eparams" to String(HexUtil.encodeHex(aesEncrypt(param, linuxApiKey))).uppercase()
        )
    }


    fun eApi(url: String, param: String): Map<String, String> {
        val message = "nobody${url}use${param}md5forencrypt"
        val digest = String(HexUtil.encodeHex(MessageDigest.getInstance("MD5").digest(message.toByteArray())))
        val data = "${url}-36cd479b6b5-${param}-36cd479b6b5-${digest}"
        return mapOf(
            "params" to String(HexUtil.encodeHex(aesEncrypt(data, eapiKey))).uppercase()
        )
    }

//    fun weApi(param: String):Map<String,String>{
//        val base62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
//        val presentKey = "0CoJUm6Qyw8W8jud"
//        val iv = "0102030405060708"
//        val secretKey = RandomUtil.randomBytes(16).map {
//            val num = abs(it.toInt())
//            Character.codePointAt(base62, num%62)
//        }.joinToString("")
//        return mapOf(
//            "params" to Base64.encode(
//                aesEncrypt(
//                    Base64.encode(
//                        aesEncrypt(param,presentKey,iv)
//                    ), secretKey,iv
//                )
//            ),
//            "encSecKey" to
//        )
//    }
}