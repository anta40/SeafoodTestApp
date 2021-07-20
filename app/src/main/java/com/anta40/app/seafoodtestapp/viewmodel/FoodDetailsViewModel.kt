package com.anta40.app.seafoodtestapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anta40.app.seafoodtestapp.data.FoodList
import com.anta40.app.seafoodtestapp.model.FoodDetail
import com.anta40.app.seafoodtestapp.model.FoodDetails
import com.anta40.app.seafoodtestapp.repository.FoodDetailsRepository
import com.anta40.app.seafoodtestapp.repository.FoodRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetailsViewModel: ViewModel() {
    private val _foodDetailsLiveData = MutableLiveData<FoodDetails>()
    val foodDetailsLiveData: LiveData<FoodDetails> = _foodDetailsLiveData

    fun getData(food_id: String){

        // viewModelScope.launch {
        FoodDetailsRepository.getFoodDetailsApiCall(food_id).enqueue(object: Callback<FoodDetails> {
            override fun onResponse(
                call: Call<FoodDetails>,
                response: Response<FoodDetails>
            ) {
                val body = response.body()
                _foodDetailsLiveData.value = response.body()
            }

            override fun onFailure(call: Call<FoodDetails>, t: Throwable) {
                Log.e("FoodDetailsViewModel", "onFailure: ", t)
            }
        })
        //  }


    }

}