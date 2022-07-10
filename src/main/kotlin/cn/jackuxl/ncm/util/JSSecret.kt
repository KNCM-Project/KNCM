package cn.jackuxl.ncm.util

import com.github.kittinunf.fuel.core.Parameters
import javax.script.Invocable
import javax.script.ScriptEngineManager
import javax.script.ScriptEngine
import java.lang.Exception
import jdk.nashorn.api.scripting.ScriptObjectMirror
import java.io.File

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
}