package com.anta40.app.seafoodtestapp.adapter

import com.anta40.app.seafoodtestapp.data.Food

interface FoodClickListener {
    fun onRowClicked(data: Food)
}
