package com.utf8coding.healthcare.networkRelated
import android.content.Context
import com.utf8coding.healthcare.MyApplication
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class AddCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val cookie: String? = MyApplication.context.getSharedPreferences(
            "cookie",
            Context.MODE_PRIVATE
        ).getString("cookie", "")

        builder.addHeader("Cookie", cookie)
        return chain.proceed(builder.build())
    }
}