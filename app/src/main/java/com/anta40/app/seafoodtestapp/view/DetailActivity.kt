package com.anta40.app.seafoodtestapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.anta40.app.seafoodtestapp.R
import com.anta40.app.seafoodtestapp.adapter.FoodAdapter
import com.anta40.app.seafoodtestapp.viewmodel.FoodDetailsViewModel
import com.anta40.app.seafoodtestapp.viewmodel.FoodListViewModel

class DetailActivity : AppCompatActivity() {

    private val vm: FoodDetailsViewModel by lazy { FoodDetailsViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val food_id = intent.getIntExtra("food_id", 0)

        vm.getData(food_id)
        observe()
    }

    fun observe(){
        vm.foodDetailsLiveData.observe(this) {
            if (it.details.isEmpty()){
                Toast.makeText(applicationContext, "Data kosong", Toast.LENGTH_SHORT).show()
            }
            else  {
                val details = it.details.get(0)
                System.out.println("Nama makanan: ${details.name}")
                System.out.println("Area makanan: ${details.area}")
                System.out.println("Kategeori makanan: ${details.category}")
            }
        }
    }
}