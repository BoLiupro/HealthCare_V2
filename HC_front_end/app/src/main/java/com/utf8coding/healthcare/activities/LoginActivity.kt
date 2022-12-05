package com.utf8coding.healthcare.activities

import android.animation.Animator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View.*
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.github.ybq.android.spinkit.SpinKitView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.utf8coding.healthcare.R
import com.utf8coding.healthcare.utils.DensityUtils
import com.utf8coding.healthcare.view_models.LoginActivityViewModel
import com.utf8coding.healthcare.view_models.LoginActivityViewModel.Companion.CONNECTION_FAIL
import com.utf8coding.healthcare.view_models.LoginActivityViewModel.Companion.SUCCESS
import com.utf8coding.healthcare.view_models.LoginActivityViewModel.Companion.WRONG_PASSWORD
import com.utf8coding.healthcare.view_models.LoginActivityViewModel.Companion.WRONG_USERNAME
import java.lang.Thread.sleep
import kotlin.concurrent.thread


@Suppress("TypeParameterFindViewById")
class LoginActivity : BaseActivity() {
    lateinit var viewModel: LoginActivityViewModel
    private val loginButton: Button
        get() {
        return findViewById(R.id.loginButton) as Button
        }
    private val registerButton: Button
        get() {
            return findViewById(R.id.registerButton) as Button
        }
    private val enterRegisterButton: TextView
        get() {
            return findViewById(R.id.enterRegisterButton) as TextView
        }
    private val userNameEditText: TextInputEditText
        get() {
            return findViewById(R.id.userName)
        }
    private val passWordEditText: TextInputEditText
        get() {
            return findViewById(R.id.password)
        }
    private val passWordCheckEditText: TextInputEditText
        get() {
            return findViewById(R.id.passwordCheck)
        }
    private val passWordCheckLayout: TextInputLayout
        get() {
            return findViewById(R.id.passwordCheckLayout)
        }
    private val loginBackGround: ConstraintLayout
        get() {
            return findViewById(R.id.loginBackGround)
        }
    private val backButton: ImageButton
        get() {
            return findViewById(R.id.backButton) as ImageButton
        }
    private val loadingIndicator: SpinKitView
        get(){
            return findViewById(R.id.loadingIndicator)
        }
    private val bgLeaves: ImageView
        get() {
            return findViewById(R.id.bgLeaves) as ImageView
        }
    private val bgLeaves2: ImageView
        get() {
            return findViewById(R.id.bgLeaves2) as ImageView
        }
    private val passWord: String
        get() {
            return passWordEditText.text.toString()
        }
    private val userName: String
        get() {
            return userNameEditText.text.toString()
        }

    private var isInRegister = false
    companion object{
        const val ANIMATION_DURATION: Long = 400
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.statusBarColor = Color.TRANSPARENT
        viewModel = ViewModelProvider(this)[LoginActivityViewModel::class.java]

        window.statusBarColor = getColor(R.color.active_green)

        enterRegisterButton.setOnClickListener{
            onEnterRegisterButtonPressed()
        }
        backButton.setOnClickListener{
            onRegisterBackPressed()
        }
        loginButton.setOnClickListener {
            onLoginButtonPressed()
        }

        registerButton.setOnClickListener {
            getRegisterStatus()
        }

        passWordCheckEditText.setOnEditorActionListener { p0, _, _ ->
            viewModel.passWordCheck = p0?.text.toString()
            false
        }
    }


    private fun onLoginButtonPressed(){
        //加载图标
        if (userNameEditText.text.toString() != "" && userNameEditText.text.toString() != "" ){
            loadingIndicator.alpha = 0f
            loadingIndicator.visibility = VISIBLE
            loadingIndicator.animate().alpha(1f)
            val manager =
                applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(
                currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )

            viewModel.login (userName, passWord, object: LoginActivityViewModel.LoginListener{
                fun enterAnimation(){
                    loadingIndicator.animate().alpha(0f)
                    loginBackGround.animate()
                        .alpha(0f)
                        .scaleY(1.5f)
                        .scaleX(1.5f)
                        .setInterpolator(AccelerateInterpolator())
                        .setStartDelay(80)
                        .duration = 1000
                    bgLeaves.animate()
                        .yBy(DensityUtils.dp2px(this@LoginActivity, 200f))
                        .xBy(DensityUtils.dp2px(this@LoginActivity, -100f))
                        .alpha(0f)
                        .scaleY(2.2f)
                        .scaleX(2.2f)
                        .setInterpolator(AccelerateInterpolator())
                        .setStartDelay(80)
                        .duration = 1000
                    bgLeaves2.animate()
                        .yBy(DensityUtils.dp2px(this@LoginActivity, -180f))
                        .xBy(DensityUtils.dp2px(this@LoginActivity, 90f))
                        .alpha(0f)
                        .scaleY(1.3f)
                        .scaleX(1.3f)
                        .setInterpolator(AccelerateInterpolator())
                        .setStartDelay(80)
                        .duration = 1000
                }
                fun startMainActivity(){
                    thread {
                        sleep(450)
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onResponse(conditionCode: Int) {
                    when(conditionCode){
                        SUCCESS -> {
                            enterAnimation()
                            startMainActivity()
                        }

                        CONNECTION_FAIL -> {
                            loadingIndicator.animate().alpha(0f)
                            //除了网络错误，还有无法解析会导致调用。
                            showAlterDialog("网络错误", "检查下网络,不行的话求助开发者")
                        }

                        WRONG_PASSWORD -> {
                            loadingIndicator.animate().alpha(0f)
                            showAlterDialog("用户名或密码错误", "为你清空密码？", true)
                            passWordCheckEditText.setText("")
                        }

                        WRONG_USERNAME -> {
                            loadingIndicator.animate().alpha(0f)
                            showAlterDialog("用户名不存在", "再想想看？")}
                    }
                }
            })
        }
    }
    private fun onEnterRegisterButtonPressed(){
        makeILog("register pressed")
        isInRegister = true

        //animation:
        //enterRegisterButton:
        enterRegisterButton.animate()
            .alpha(0f).duration = ANIMATION_DURATION

        //loadingIndicator:
        loadingIndicator.animate()
            .yBy(DensityUtils.dp2px(this, 50f)).duration = ANIMATION_DURATION

        //loginButton, pasCheck:
        loginButton.animate()
            .alpha(0f)
            .yBy(DensityUtils.dp2px(this, 80f)).duration = ANIMATION_DURATION

        passWordCheckLayout.alpha = 0f
        passWordCheckLayout.visibility = VISIBLE
        passWordCheckLayout.animate().alpha(1f)
            .setListener(EmptyAnimationListener())
            .setStartDelay(ANIMATION_DURATION/2).duration = ANIMATION_DURATION - ANIMATION_DURATION/2

        //registerButton, pasCheck:
        registerButton.alpha = 0f
        registerButton.visibility = VISIBLE
        registerButton.animate()
            .setListener(EmptyAnimationListener())
            .alpha(1f)
            .setDuration(ANIMATION_DURATION)
            .yBy(DensityUtils.dp2px(this, 80f)).duration = ANIMATION_DURATION

        //backButton:
        backButton.visibility = VISIBLE
        backButton.animate().alpha(1f).duration = ANIMATION_DURATION
    }
    private fun onRegisterBackPressed(){
        makeILog("registerBack pressed")
        isInRegister = false

        //START - animation:
        //enterRegisterButton:
        enterRegisterButton.alpha = 0f
        enterRegisterButton.visibility = VISIBLE
        enterRegisterButton.animate()
            .alpha(1f).duration = ANIMATION_DURATION

        //loadingIndicator:
        loadingIndicator.animate()
            .yBy(DensityUtils.dp2px(this, -50f)).duration = ANIMATION_DURATION

        //loginButton, pasCheck:
        loginButton.animate()
            .alpha(1f)
            .yBy(DensityUtils.dp2px(this, -80f)).duration = ANIMATION_DURATION
        passWordCheckLayout.animate().setStartDelay(0)
            .setListener(OnAnimationEndListener {
                passWordCheckLayout.visibility = INVISIBLE
            })
            .alpha(0f).duration = ANIMATION_DURATION/2

        //registerButton:
        registerButton.animate()
            .alpha(0f)
            .setDuration((ANIMATION_DURATION - 0.5*ANIMATION_DURATION).toLong())
            .setListener(OnAnimationEndListener {
                passWordCheckLayout.visibility = INVISIBLE
            })
            .yBy(DensityUtils.dp2px(this, -80f)).duration = ANIMATION_DURATION

        //registerBackButton:
        backButton.animate().alpha(0f).duration = ANIMATION_DURATION
        //END - animation
    }
    private fun getRegisterStatus(){
        loadingIndicator.alpha = 0f
        loadingIndicator.visibility = VISIBLE
        loadingIndicator.animate().alpha(1f)
    }

    //life cycle:
    override fun onResume() {
        userNameEditText.setText(viewModel.userName)
        passWordEditText.setText(viewModel.passWord)
        passWordCheckEditText.setText(viewModel.passWordCheck)
        super.onResume()
    }

    //overrides：
    override fun onBackPressed() {
        if (isInRegister){
            onRegisterBackPressed()
        } else {
            ActivityCollector.finishAll()
        }
    }

    //little tools:
    private fun makeILog(msg: String){
        Log.i("Login activity:", msg)
    }
    private fun showAlterDialog(title: String, content: String, isPas: Boolean = false) {
        val alterDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alterDialog.setTitle(title)
        alterDialog.setMessage(content)
        //显示
        if (isPas){
            alterDialog.setPositiveButton("好的"){ _,_ ->
                passWordEditText.setText("")
            }

            alterDialog.setNegativeButton("不用"){ _, _ ->}
        } else {
            alterDialog.setPositiveButton("好的"){ _, _ ->}
        }
        alterDialog.show()
    }

    inner class EmptyAnimationListener: Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {}
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
    }

    inner class OnAnimationEndListener(onAnimationEndLambda: () -> Unit): Animator.AnimatorListener{
        var onAnimationEndContent: () -> Unit
        init {
            onAnimationEndContent = onAnimationEndLambda
        }
        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {onAnimationEndContent()}
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
    }
}