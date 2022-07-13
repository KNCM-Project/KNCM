package cn.jackuxl.ncm.util

import com.github.kittinunf.fuel.core.Parameters
import jdk.nashorn.api.scripting.ScriptObjectMirror
import java.io.File
import javax.script.Invocable
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

// Thanks to https://github.com/leftvalue/NeteaseApi/blob/master/NeteaseApi/src/secret/JSSecret.java

object JSSecret {
    private lateinit var inv: Invocable
    private const val encText = "encText"
    private const val encSecKey = "encSecKey"

    /*
      从本地加载修改后的 js 文件到 scriptEngine
     */
    init {
        try {
            val js = File("core.js").readText()
            val factory = ScriptEngineManager()
            val engine: ScriptEngine = factory.getEngineByName("JavaScript")
            engine.eval(js)
            inv = engine as Invocable
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun transData(paras: String): Parameters {
        val params = inv.invokeFunction("myFunc", paras) as ScriptObjectMirror
        return listOf(
            "params" to params[encText].toString(),
            "encSecKey" to params[encSecKey].toString()
        )
    }

//    fun eapi(url:String, params:String):Parameters{
//        val message = "nobody${url}use${params}md5forencrypt"
//        val messageDigest = SecureUtil.createMessageDigest("MD5")
//        messageDigest.update(message.toByte())
//        val data = "${url}-36cd479b6b5-${params}-36cd479b6b5-${messageDigest.digest("hex".toByteArray())}"
//        val eapiKey = "e82ckenh8dichen8"
//
//        val cipher = SecureUtil.createCipher("aes-128-ecb")
//        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(eapiKey.toByteArray(),)
//
//    }

}