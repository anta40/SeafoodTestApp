package com.anta40.app.seafoodtestapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anta40.app.seafoodtestapp.R
import com.anta40.app.seafoodtestapp.data.Food
import com.bumptech.glide.Glide

class FoodAdapter(private val context: Context, private val dataSet: List<Food>, private val clickListener: FoodClickListener) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtDescription: TextView
        val imgThumbnail: ImageView

        init {
            txtDescription = view.findViewById(R.id.food_description)
            imgThumbnail = view.findViewById(R.id.food_thumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtDescription.text = dataSet[position].description
        val imgUrl = dataSet[position].thumbnailUrl

        Glide.with(context)
            .load(imgUrl)
            .override(200, 150)
            .into(holder.imgThumbnail);

        holder.itemView.setOnClickListener {
            clickListener.onRowClicked(dataSet[position])
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}