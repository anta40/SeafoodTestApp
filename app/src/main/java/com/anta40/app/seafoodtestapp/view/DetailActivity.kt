package com.anta40.app.seafoodtestapp.view

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
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
    private lateinit var food_detail_ingredients: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        food_detail_img = binding.foodDetailsImg
        food_details_instructions = binding.foodDetailsInstructions
        food_detail_ingredients = binding.txtIngredients

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

                food_detail_ingredients.append(details.ingredient1 + " : " +details.measure1)
                food_detail_ingredients.append(", ");
                food_detail_ingredients.append(details.ingredient2 + " : " +details.measure2)
                food_detail_ingredients.append(", ");
                food_detail_ingredients.append(details.ingredient3 + " : " +details.measure3)
                food_detail_ingredients.append(", ");
                food_detail_ingredients.append(details.ingredient4 + " : " +details.measure4)
                food_detail_ingredients.append(", ");
                food_detail_ingredients.append(details.ingredient5 + " : " +details.measure5)
                food_detail_ingredients.append(", ");
                food_detail_ingredients.append(details.ingredient6 + " : " +details.measure6)
                food_detail_ingredients.append(", ");
                food_detail_ingredients.append(details.ingredient7 + " : " +details.measure7)
                food_detail_ingredients.append(", ");
                food_detail_ingredients.append(details.ingredient8+ " : " +details.measure8)
                food_detail_ingredients.append(", ");
                food_detail_ingredients.append(details.ingredient9+ " : " +details.measure9)
                food_detail_ingredients.append(", ");
                food_detail_ingredients.append(details.ingredient10+ " : " +details.measure10)

                food_details_instructions.setText(details.instructions)

                Glide.with(applicationContext)
                    .load(details.thumbnailUrl)
                    .override(600, 600)
                    .centerCrop()
                    .into(food_detail_img);

                food_detail_img.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(details.youtubeUrl));
                    intent.flags = FLAG_ACTIVITY_NEW_TASK
                    applicationContext.startActivity(intent)
                }
            }
        }
    }
}