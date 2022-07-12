package cn.jackuxl.ncm.util

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Base64
import java.lang.RuntimeException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

val eapiKey = "e82ckenh8dichen8"
val iv = "0102030405060708"

object Crypto {
    fun aes(value: String, mode: String, cipherMode: Int, key: String, iv: String): String {
        val base64Encoder = Base64.getEncoder()
        val base64Decoder = Base64.getDecoder()
        val cipher = Cipher.getInstance("AES/$mode/PKCS5PADDING")
        val keySpec = SecretKeySpec(base64Decoder.decode(key), "AES")

        if (mode == "ECB") {
            cipher.init(cipherMode, keySpec)
        }else if (mode == "CBC") {
            val ivSpec = IvParameterSpec(base64Decoder.decode(iv))
            cipher.init(cipherMode, keySpec, ivSpec)
        }else {
            throw RuntimeException("Illegal Crypt Mode")
        }

        if (cipherMode == Cipher.ENCRYPT_MODE) {
            return base64Encoder.encodeToString(cipher.doFinal(value.toByteArray()))
        } else if (cipherMode == Cipher.DECRYPT_MODE) {
            val valueDecoded = base64Decoder.decode(value)
            return base64Encoder.encodeToString(cipher.doFinal(valueDecoded))
        }
        throw RuntimeException("Illegal Cipher Mode")
    }

    /*fun eapi(url: String, objects: Any) {
        val text = Json.encodeToString(objects)
        val message = "nobody${url}use${text}md5forencrypt"
    }*/
}