package com.utf8coding.healthcare

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    lateinit var session: String
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
