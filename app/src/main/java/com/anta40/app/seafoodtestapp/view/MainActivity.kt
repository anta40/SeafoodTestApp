package com.anta40.app.seafoodtestapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anta40.app.seafoodtestapp.R
import com.anta40.app.seafoodtestapp.adapter.FoodAdapter
import com.anta40.app.seafoodtestapp.adapter.FoodClickListener
import com.anta40.app.seafoodtestapp.data.Food
import com.anta40.app.seafoodtestapp.databinding.ActivityMainBinding
import com.anta40.app.seafoodtestapp.viewmodel.FoodListViewModel

class MainActivity : AppCompatActivity(), FoodClickListener {

    private val vm: FoodListViewModel by lazy { FoodListViewModel() }
    private lateinit var binding: ActivityMainBinding
    private lateinit var recview_food: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recview_food = binding.recviewFoodList

        vm.getData()
        observe()
    }

    fun observe(){
        vm.foodListLiveData.observe(this) {
            if (it.foods.isEmpty()){
                Toast.makeText(applicationContext, "Data kosong", Toast.LENGTH_SHORT).show()
            }
            else  {
                val foodAdapter = FoodAdapter(applicationContext, it.foods, this)
                recview_food.layoutManager = LinearLayoutManager(applicationContext,
                    LinearLayoutManager.VERTICAL, false)
                recview_food.adapter = foodAdapter
            }
        }
    }

    override fun onRowClicked(data: Food) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("food_id", data.id)
        intent.putExtra("food_name", data.description)
        startActivity(intent)
    }
}