package com.anta40.app.seafoodtestapp.repository

import com.anta40.app.seafoodtestapp.data.ApiClient
import com.anta40.app.seafoodtestapp.data.FoodList
import com.anta40.app.seafoodtestapp.model.FoodDetails
import retrofit2.Call

object FoodDetailsRepository {
    fun getFoodDetailsApiCall(food_id: Int): Call<FoodDetails> {
        val call = ApiClient.apiInterface.getFoodDetails(food_id)
        return call
    }
}