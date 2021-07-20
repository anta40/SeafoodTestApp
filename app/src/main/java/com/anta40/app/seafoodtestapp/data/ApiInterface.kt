package com.anta40.app.seafoodtestapp.data

import com.anta40.app.seafoodtestapp.model.FoodDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("filter.php")
    fun getFoodList(@Query("c") food_type: String) : Call<FoodList>

    @GET("lookup.php")
    fun getFoodDetails(@Query("i") food_id:Int): Call<FoodDetails>
}