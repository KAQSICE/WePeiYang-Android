package xyz.rickygao.gpa2.spider

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.jsoup.Jsoup
import java.io.IOException

class User( private val context: Context) {
    private val BASE_URL = "https://sso.tju.edu.cn/cas/login"
    private val url = "$BASE_URL?service=http://classes.tju.edu.cn/eams/homeExt.action"
    private val logoutUrl = "http://classes.tju.edu.cn/eams/logoutExt.action"
    fun login(userName: String, password: String, execution: String) =
            GlobalScope.launch {
                var okHttpClient = OkHttpClientGenerator.generate(context)
                var requestBody = FormBody.Builder()
                        .add("username", userName)
                        .add("password", password)
                        .add("_eventId", "submit")
                        .add("execution", execution)
                        .build()
                var request = Request.Builder().url(url).post(requestBody).build()
                okHttpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                        GlobalScope.launch(Dispatchers.Main) {
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.d("cookie", response.header("Set-Cookie") ?: "null")
                        GlobalScope.launch(Dispatchers.Main) {
                        }
                    }

                })
            }


    fun logout() {
        GlobalScope.launch {
            var doc = Jsoup.connect(logoutUrl).get()
            Log.d("logout", doc.toString())
        }
    }
}