package com.utf8coding.healthcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.networkRelated.NetworkUtils
import java.lang.Thread.sleep
import kotlin.concurrent.thread

@Suppress("TypeParameterFindViewById")
class LauncherActivity : BaseActivity() {
    private val launcherImageView: ImageView
        get(){
            return findViewById(R.id.launcherImageView) as ImageView
        }
    private val skipButton: Button
        get() {
            return findViewById(R.id.skipButton) as Button
        }
    private var isConnection = false
    private var isAutoStart = true
    private var countDown = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        Glide.with(this).load("https://s1.ax1x.com/2022/04/07/qz31zt.jpg").into(launcherImageView)
        checkConnectivity()
        //跳过倒计时按钮逻辑：
        skipButton.setOnClickListener{
            isAutoStart = false
            startNextActivity()
            finish()
        }
        //倒计时：开线程，自动启动：
        thread {
            sleep(1000)
            runOnUiThread{
                skipButton.text = "跳过(2)"
            }
            countDown -= 1
            sleep(1000)
            runOnUiThread{
                skipButton.text = "跳过(1)"
            }
            countDown -= 1
            sleep(1000)
            if(isAutoStart){
                startNextActivity()
            }
        }
    }

    private fun checkConnectivity(){
        NetworkUtils.isNetworkConnected(
            onSuccess = {
                isConnection = true
            },
            onFailure = {
                isConnection = false
            }
        )
    }

    //启动不同Activity的方法：
    private fun startNextActivity(){
        if (isConnection) {
            val pref = this.getSharedPreferences("userData", Context.MODE_PRIVATE)
            val userName = pref.getString("userName", "")?: ""
            val passWord = pref.getString("passWord", "")?: ""
            val id = pref.getInt("id", -1)
            Log.i("launcher activity:", "auto logging in, userName: $userName, password: $passWord, id: $id")
            if (userName != "" && passWord != ""){
                NetworkUtils.login(userName, passWord, object: NetworkUtils.LoginNetListener{
                    override fun onSuccess() {
                        startMainActivity()
                    }
                    override fun onFailure() {
                        startLoginActivity()
                    }
                    override fun onWrongUser() {
                        startLoginActivity()
                    }
                    override fun onWrongPassword() {
                        startLoginActivity()
                    }
                })
            } else {
                startLoginActivity()
            }
        }  else {
            startLoginActivity()
        }

    }
    private fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun startLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    //overrides:
    override fun onPause() {
        super.onPause()
        isAutoStart = false
    }
    override fun onResume() {
        super.onResume()
        if(!isAutoStart){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        ActivityCollector.finishAll()
    }
}