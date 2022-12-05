package com.utf8coding.healthcare.view_models

import android.text.BoringLayout
import android.util.Log
import androidx.lifecycle.ViewModel
import com.utf8coding.healthcare.networkRelated.NetworkUtils

class LoginActivityViewModel: ViewModel() {
    companion object{
        const val SUCCESS = 1
        const val CONNECTION_FAIL = 2
        const val WRONG_PASSWORD = 3
        const val WRONG_USERNAME = 4
    }
    var userName: String = ""
    var passWord: String = ""
    var passWordCheck: String = ""

    interface LoginListener{
        fun onResponse(conditionCode: Int)
    }
    fun login(userName: String, passWord: String, listener: LoginListener, isForce: Boolean = false){
        Log.i("LoginActivityViewModel:", "logging: $passWord, $userName")
        NetworkUtils.login(userName, passWord, object: NetworkUtils.LoginNetListener {
            override fun onSuccess() {
                listener.onResponse(SUCCESS)
             }
            override fun onFailure() {
                listener.onResponse(CONNECTION_FAIL)
             }
            override fun onWrongUser() {
                listener.onResponse(WRONG_USERNAME)
            }
            override fun onWrongPassword() {
                listener.onResponse(WRONG_PASSWORD)
            }
        })
    }
}