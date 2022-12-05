package com.utf8coding.healthcare.networkRelated

import com.utf8coding.healthcare.data.EpidemicData
import com.utf8coding.healthcare.data.LifeIndexData
import com.utf8coding.healthcare.data.UserData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    @GET("/lifeIndex")
    fun getLifeIndex(): Call<LifeIndexData>

    @GET("/epidamic")
    fun getEpidemicData(): Call<EpidemicData>

    @POST("login")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun login(@Field("username") userName: String, @Field("password") passWord: String): Call<NetWorkResponse<UserData>>

    //这个其实好像用不到一般
    @POST("/essays/getEssayById")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getArticleById(@Field("essay_id") articleId: Int): Call<NetWorkResponse<NetWorkResponse.Essay>>

    @POST("/essays/recommend")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getSuggestedArticle(@Field("user_id") userId: Int): Call<NetWorkResponse<NetWorkResponse.EssayList>>

    @POST("/essays/getEssayByKey")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun searchArticle(@Field("searchKey")searchKey: String): Call<NetWorkResponse<NetWorkResponse.EssayList>>

    @POST("/users/getCollection")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getCollection(@Field("user_id")searchKey: Int): Call<NetWorkResponse<NetWorkResponse.CollectionList>>

    @POST("/users/checkCollection")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun checkCollection(@Field("user_id")userId: Int, @Field("essay_id")articleId: Int): Call<NetWorkResponse<Any>>

    @POST("/users/setCollection")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun setCollection(@Field("user_id")userId: Int, @Field("essay_id")articleId: Int): Call<NetWorkResponse<Any>>

    @POST("/users/deleteCollection")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun deleteCollection(@Field("user_id")userId: Int, @Field("essay_id")articleId: Int): Call<NetWorkResponse<Any>>

    @POST("/drugs/selectByName")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun searchDrugByName(@Field("name")drugName: String): Call<NetWorkResponse<NetWorkResponse.MedDataList>>

    @POST("/drugs/selectByType")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun searchDrugByType(@Field("type")drugType: String): Call<NetWorkResponse<NetWorkResponse.MedDataList>>

    @POST("/drugs/selectByProducer")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun searchDrugByMedManufacturer(@Field("producer")drugManufacturer: String): Call<NetWorkResponse<NetWorkResponse.MedDataList>>

    @POST("/comments/getCheckedComments")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getComments(@Field("essay_id")articleId: Int): Call<NetWorkResponse<NetWorkResponse.CommentList>>

    @POST("/comments/putComments")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun setComment(@Field("user_id") userId: Int, @Field("essay_id")articleId: Int, @Field("comment")comment: String): Call<NetWorkResponse<ResponseBody>>
}