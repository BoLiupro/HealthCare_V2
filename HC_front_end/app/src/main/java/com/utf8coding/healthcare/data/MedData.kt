package com.utf8coding.healthcare.data

import com.bumptech.glide.load.model.FileLoader
import com.google.gson.annotations.SerializedName

data class MedData(val id: Int, val name: String,
                   val type: String, val quantity: String,
                   val price: Float, @SerializedName("producer") val manufacturer: String
                   )