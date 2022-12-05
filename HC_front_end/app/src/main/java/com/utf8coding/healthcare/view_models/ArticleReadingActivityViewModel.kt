package com.utf8coding.healthcare.view_models

import android.app.PendingIntent
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utf8coding.healthcare.MyApplication
import com.utf8coding.healthcare.data.ArticleData
import com.utf8coding.healthcare.data.CommentData
import com.utf8coding.healthcare.networkRelated.NetworkUtils

class ArticleReadingActivityViewModel: ViewModel() {
    var isCollected: MutableLiveData<Boolean> = MutableLiveData(false)
    var commentData: MutableLiveData<ArrayList<CommentData>> = MutableLiveData<ArrayList<CommentData>>(ArrayList())

    fun getCommentList(articleData: ArticleData): MutableLiveData<ArrayList<CommentData>>{
        NetworkUtils.getComment(articleData.id,
            onSuccess = { mCommentList: ArrayList<CommentData> -> commentData.value = mCommentList },
            onFailure = {
                Toast.makeText(MyApplication.context, "评论获取失败！", Toast.LENGTH_SHORT).show()
            })
        return commentData
    }

    fun collectArticle(articleData: ArticleData){
        NetworkUtils.setCollection(getUserId(), articleData.id)
    }

    fun unCollectArticle(articleData: ArticleData){
        NetworkUtils.deleteCollection(getUserId(), articleData.id)
    }

    fun getIsCollected(articleData: ArticleData): MutableLiveData<Boolean>{
        NetworkUtils.checkCollected(getUserId(), articleData.id,
            onFeedBack = {
                isCollected.value = it
            })
        return isCollected
    }

    fun sendComment(content: String ,articleId:Int){
        NetworkUtils.setComment(getUserId(), articleId, content)
    }

    private fun getUserId(): Int{
        return MyApplication.context.getSharedPreferences("userData", Context.MODE_PRIVATE).getInt("userId", -1)
    }
}