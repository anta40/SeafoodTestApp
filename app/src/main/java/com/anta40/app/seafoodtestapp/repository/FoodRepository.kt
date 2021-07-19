package com.anta40.app.seafoodtestapp.repository

import com.anta40.app.seafoodtestapp.data.ApiClient
import com.anta40.app.seafoodtestapp.data.FoodList
import retrofit2.Call

object FoodRepository {

    suspend fun getFoodListApiCall(): Call<FoodList> {

        val call = ApiClient.apiInterface.getFoodList("Seafood")

        return call
    }

}