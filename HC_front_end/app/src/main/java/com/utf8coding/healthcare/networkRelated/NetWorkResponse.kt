package com.utf8coding.healthcare.networkRelated

import com.google.gson.annotations.SerializedName
import com.utf8coding.healthcare.data.ArticleData
import com.utf8coding.healthcare.data.CommentData
import com.utf8coding.healthcare.data.MedData
import kotlin.collections.ArrayList

class NetWorkResponse<T>(val success: Boolean, val code: Int, val message: String, val data: T){
    data class Essay(val essay: ArticleData)
    data class EssayList(@SerializedName("essay")val essayList: ArrayList<ArticleData>)
    data class CollectionList(@SerializedName("collections")val list: ArrayList<Collection>)
    data class Collection(@SerializedName("essayId")val articleId: Int)
    data class MedDataList(@SerializedName("drugs")val medList: ArrayList<MedData>)
    data class CommentList(@SerializedName("CheckedComments")val commentList: ArrayList<CommentData>)
}