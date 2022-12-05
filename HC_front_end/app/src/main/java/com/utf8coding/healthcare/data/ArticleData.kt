package com.utf8coding.healthcare.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleData(@SerializedName("essayId") val id: Int, val title: String, @SerializedName("text") val content: String,@SerializedName("picture") val headPicUrl: String?): Serializable
