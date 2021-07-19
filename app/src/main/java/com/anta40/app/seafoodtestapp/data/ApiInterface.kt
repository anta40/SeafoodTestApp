package com.anta40.app.seafoodtestapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("filter.php")
    suspend fun getFoodList(@Query("c") food_type: String) : Call<FoodList>
}