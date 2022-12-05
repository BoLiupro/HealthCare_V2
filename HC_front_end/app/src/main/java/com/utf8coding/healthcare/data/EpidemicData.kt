package com.utf8coding.healthcare.data

import com.google.gson.annotations.SerializedName

data class EpidemicData(@SerializedName("境外输入") val outOfBorder: String,@SerializedName("无症状感染")  val noSymp: String,
                        @SerializedName("确诊")  val diagnosed: String,@SerializedName("治愈")  val cured: String,
                        @SerializedName("时间") val time: String) {
    override fun toString(): String{
        return "outOfBorder: $outOfBorder, noSymp: $noSymp, diagnosed: $diagnosed, cured: $cured, time: $time"
    }
}
