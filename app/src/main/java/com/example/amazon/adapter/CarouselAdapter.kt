package com.example.amazon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amazon.databinding.ActivityMainBinding
import com.example.amazon.databinding.CarouselItemsBinding
import com.example.amazon.models.CarouselModel

class CarouselAdapter(private val imageList: List<CarouselModel>): RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    class CarouselViewHolder(val binding: CarouselItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = CarouselItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val image = imageList[position]
        holder.binding.apply {
            Glide.with(imageSrc).load(imageList[position].image).into(imageSrc)
            imageName.text = image.name
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}