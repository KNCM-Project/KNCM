package cn.jackuxl.kncm.util


import cn.hutool.core.codec.Base64
import cn.hutool.core.util.HexUtil
import cn.hutool.core.util.RandomUtil
import cn.hutool.crypto.SecureUtil
import java.math.BigInteger
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.spec.RSAPublicKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.math.abs


object Crypto {
    private const val eApiKey = "e82ckenh8dichen8"
    private const val linuxApiKey = "rFgB&h#%2?^eDg:Q"
    private val iv = "0102030405060708".toByteArray()

    private fun aesEncrypt(value: String, key: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
        val keySpec = SecretKeySpec(key, "AES")
        val cipherMode = Cipher.ENCRYPT_MODE
        cipher.init(cipherMode, keySpec)
        return cipher.doFinal(value.toByteArray())
    }

    private fun aesEncrypt(value: String, key: ByteArray, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(key, "AES")
        val cipherMode = Cipher.ENCRYPT_MODE
        val ivSpec = IvParameterSpec(iv)
        cipher.init(cipherMode, keySpec, ivSpec)
        return cipher.doFinal(value.toByteArray())
    }

    private fun rsaEncrypt(value: String, modulus: BigInteger, pubExp: BigInteger): ByteArray {
        val cipher = Cipher.getInstance("RSA/ECB/NoPadding")
        val pubKeySpec = RSAPublicKeySpec(modulus, pubExp)
        val a = SecureUtil.rsa(
            null,
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgtQn2JZ34ZC28NWYpAUd98iZ37BUrX/aKzmFbt7clFSs6sXqHauqKWqdtLkF2KexO40H1YTX8z2lSgBBOAxLsvaklV8k4cBFK9snQXE9/DDaFt6Rr7iVZMldczhC0JNgTz+SHXT6CBHuX3e9SdB1Ua44oncaTWz7OBGLbCiK45wIDAQAB"
        )
        val keyFactory = KeyFactory.getInstance("RSA")
        val key = keyFactory.generatePublic(pubKeySpec)

        cipher.init(Cipher.ENCRYPT_MODE, key)
        return cipher.doFinal(value.toByteArray())
    }

    fun linuxApi(param: String): Map<String, String> {
        return mapOf(
            "eparams" to String(HexUtil.encodeHex(aesEncrypt(param, linuxApiKey.toByteArray()))).uppercase()
        )
    }


    fun eApi(url: String, param: String): Map<String, String> {
        val message = "nobody${url}use${param}md5forencrypt"
        val digest = String(HexUtil.encodeHex(MessageDigest.getInstance("MD5").digest(message.toByteArray())))
        val data = "${url}-36cd479b6b5-${param}-36cd479b6b5-${digest}"
        return mapOf(
            "params" to String(HexUtil.encodeHex(aesEncrypt(data, eApiKey.toByteArray()))).uppercase()
        )
    }

    fun weApi(param: String): List<Pair<String, String>> {
        val base62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val presentKey = "0CoJUm6Qyw8W8jud".toByteArray()

        // Generated from Key.pem: "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgtQn2JZ34ZC28NWYpAUd98iZ37BUrX/aKzmFbt7clFSs6sXqHauqKWqdtLkF2KexO40H1YTX8z2lSgBBOAxLsvaklV8k4cBFK9snQXE9/DDaFt6Rr7iVZMldczhC0JNgTz+SHXT6CBHuX3e9SdB1Ua44oncaTWz7OBGLbCiK45wIDAQAB\n-----END PUBLIC KEY-----"
        val modulus = BigInteger(
            "00E0B509F6259DF8642DBC35662901477DF22677EC152B5FF68ACE615BB7B725152B3AB17A876AEA8A5AA76D2E417629EC4EE341F56135FCCF695280104E0312ECBDA92557C93870114AF6C9D05C4F7F0C3685B7A46BEE255932575CCE10B424D813CFE4875D3E82047B97DDEF52741D546B8E289DC6935B3ECE0462DB0A22B8E7",
            16
        )
        val pubExp = BigInteger("010001", 16)


        val secretKey = ByteArray(16)
        RandomUtil.randomBytes(16).forEachIndexed { index, byte ->
            secretKey[index] = Character.codePointAt(base62, abs(byte.toInt()) % 62).toByte()
        }
        return listOf(
            "params" to Base64.encode(
                aesEncrypt(
                    Base64.encode(
                        aesEncrypt(param, presentKey, iv)
                    ), secretKey, iv
                )
            ),
            "encSecKey" to String(HexUtil.encodeHex(rsaEncrypt(String(secretKey).reversed(), modulus, pubExp)))
        )
    }
}