package com.example.navigation

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryThumb")  val thumb: String,
    @SerializedName("strCategoryDescription") val description: String
)
