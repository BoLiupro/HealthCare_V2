package com.utf8coding.healthcare.data

import com.google.gson.annotations.SerializedName

data class LifeIndexData(@SerializedName("感冒指数") val fluIndex: String, @SerializedName("穿衣指数") val clothIndex: String,
                         @SerializedName("辐射指数") val radioIndex: String, @SerializedName("过敏指数") val allergyIndex: String,
                         @SerializedName("运动指数") val sportIndex: String){
    override fun toString(): String {
        return "fluIndex: $fluIndex, clothIndex: $clothIndex, radioIndex: $radioIndex, allergyIndex: $allergyIndex, sportIndex: $sportIndex"
    }
}