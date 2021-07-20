package com.anta40.app.seafoodtestapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anta40.app.seafoodtestapp.data.FoodList
import com.anta40.app.seafoodtestapp.repository.FoodRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodListViewModel: ViewModel() {

    private val _foodListLiveData = MutableLiveData<FoodList>()
    val foodListLiveData: LiveData<FoodList> = _foodListLiveData

    fun getData(){

       // viewModelScope.launch {
            FoodRepository.getFoodListApiCall().enqueue(object: Callback<FoodList> {
                override fun onResponse(
                    call: Call<FoodList>,
                    response: Response<FoodList>
                ) {
                    val body = response.body()
                    _foodListLiveData.value = response.body()
                }

                override fun onFailure(call: Call<FoodList>, t: Throwable) {
                    Log.e("FoodListViewModel", "onFailure: ", t)
                }
            })
      //  }


    }

}