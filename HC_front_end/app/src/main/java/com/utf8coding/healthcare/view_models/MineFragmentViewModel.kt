package com.utf8coding.healthcare.view_models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.utf8coding.healthcare.MyApplication

class MineFragmentViewModel : ViewModel() {
    fun getUserName(): String{
        return MyApplication.context.getSharedPreferences("userData", Context.MODE_PRIVATE).getString("userName", "")?: ""
    }

    fun getUserHeadUri(): String{
        return MyApplication.context.getSharedPreferences("userData", Context.MODE_PRIVATE).getString("userHeadUri", "")?: ""
    }

    fun getCookie(): String{
        return MyApplication.context.getSharedPreferences("cookie", Context.MODE_PRIVATE).getString("cookie", "")?: ""
    }
}