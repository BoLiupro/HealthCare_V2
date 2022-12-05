package com.utf8coding.healthcare.view_models

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.utf8coding.healthcare.MyApplication
import com.utf8coding.healthcare.data.ArticleData
import com.utf8coding.healthcare.networkRelated.NetworkUtils

class InfoFragmentViewModel: ViewModel() {
    var articleDataList: MutableLiveData<ArrayList<ArticleData>> = MutableLiveData(ArrayList())
    fun getArticleList(): MutableLiveData<ArrayList<ArticleData>>{
        NetworkUtils.getSuggestedArticle(getUserId(), { articleDataList.value = it }, {})
        return articleDataList
    }

    fun getUserId(): Int{
        return MyApplication.context.getSharedPreferences("userData", Context.MODE_PRIVATE).getInt("userId", -1)
    }
}