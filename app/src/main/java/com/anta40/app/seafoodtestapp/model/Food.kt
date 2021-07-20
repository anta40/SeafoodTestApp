package com.anta40.app.seafoodtestapp.data

import com.google.gson.annotations.SerializedName

data class Food (
    @SerializedName("strMeal") val description: String,
    @SerializedName("strMealThumb") val thumbnailUrl: String,
    @SerializedName("idMeal") val id : String
)

data class FoodList (
    @SerializedName("meals") val foods : List<Food>
)
