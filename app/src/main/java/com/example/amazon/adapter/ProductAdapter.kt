package com.example.amazon.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amazon.R
import com.example.amazon.models.ApiModel
import kotlin.math.round

class ProductAdapter(val context: Context, val articles: List<ApiModel>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }

    class ProductViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        var productImage = itemView.findViewById<ImageView>(R.id.productImage)
        var productName = itemView.findViewById<TextView>(R.id.productName)
        var productBrand = itemView.findViewById<TextView>(R.id.productBrand)
        var productPrice = itemView.findViewById<TextView>(R.id.productPrice)
        var productDiscount = itemView.findViewById<TextView>(R.id.productDiscount)
        var productDiscountedPrice = itemView.findViewById<TextView>(R.id.productDiscountedPrice)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ProductViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val articles = articles[position]
        holder.productName.text = articles.title
        holder.productBrand.text = articles.brand
        holder.productPrice.text = "$${articles.price}"
        holder.productDiscount.text = " (${ articles.discountPercentage.toString() }% off)"
        holder.productPrice.showStrikeThrough(true)
//        holder.productDiscountedPrice.text =
            var price = "${ articles.price!!.toFloat() * (100-articles.discountPercentage!!)/100}"
        holder.productDiscountedPrice.text = "$${ round(price.toDouble() * 100) / 100 } "
        Glide.with(context).load(articles.thumbnail).into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
    fun TextView.showStrikeThrough(show: Boolean) {
        paintFlags =
            if (show) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}