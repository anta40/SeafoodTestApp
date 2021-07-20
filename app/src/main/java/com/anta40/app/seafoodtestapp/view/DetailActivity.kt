package com.anta40.app.seafoodtestapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.anta40.app.seafoodtestapp.R
import com.anta40.app.seafoodtestapp.adapter.FoodAdapter
import com.anta40.app.seafoodtestapp.databinding.ActivityDetailBinding
import com.anta40.app.seafoodtestapp.viewmodel.FoodDetailsViewModel
import com.anta40.app.seafoodtestapp.viewmodel.FoodListViewModel
import com.bumptech.glide.Glide
import android.widget.TextView




class DetailActivity : AppCompatActivity() {

    private val vm: FoodDetailsViewModel by lazy { FoodDetailsViewModel() }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var food_detail_img: ImageView
    private lateinit var food_details_instructions: TextView
    private lateinit var food_detail_ingredients: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        food_detail_img = binding.foodDetailsImg
        food_details_instructions = binding.foodDetailsInstructions
        food_detail_ingredients = binding.llIngredients

        setContentView(binding.root)

        val food_id = intent.getStringExtra("food_id")!!
        val food_name = intent.getStringExtra("food_name")!!
        supportActionBar?.setTitle(food_name)

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

                val textView1 = TextView(this)
                textView1.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textView1.text = details.ingredient1 +" : " + details.measure1
                textView1.setPadding(10, 5, 10, 5)

                val textView2 = TextView(this)
                textView2.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textView2.text = details.ingredient2 +" : " + details.measure2
                textView2.setPadding(10, 5, 10, 5)

                val textView3 = TextView(this)
                textView3.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textView3.text = details.ingredient3 +" : " + details.measure3
                textView3.setPadding(10, 5, 10, 5)

                food_detail_ingredients.addView(textView1)
                food_detail_ingredients.addView(textView2)
                food_detail_ingredients.addView(textView3)

                food_details_instructions.setText(details.instructions)

                Glide.with(applicationContext)
                    .load(details.thumbnailUrl)
                    .override(600, 600)
                    .centerCrop()
                    .into(food_detail_img);
            }
        }
    }
}