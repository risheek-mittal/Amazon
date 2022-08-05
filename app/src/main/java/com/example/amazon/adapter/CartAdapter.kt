package com.example.amazon.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.amazon.database.DBHelper
import com.example.amazon.R
import com.example.amazon.fragments.CartFragment
import com.example.amazon.models.CartModel
import kotlin.collections.ArrayList


class CartAdapter(val context: Context, val articles: List<CartModel>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var productImage = itemView.findViewById<ImageView>(R.id.productImage)
        var productName = itemView.findViewById<TextView>(R.id.productName)
        var productQuantity = itemView.findViewById<TextView>(R.id.prnumber)
        var productPrice = itemView.findViewById<TextView>(R.id.productPrice)
        var increaseButton = itemView.findViewById<CardView>(R.id.more)
        var decreaseButton = itemView.findViewById<CardView>(R.id.less)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false)
        return CartViewHolder(view)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var cartList = ArrayList<String>()
        for(element in articles){
                cartList.add(element.title)
        }

        val article = articles[position]
        var quantity = article.quantity
        holder.productName.text = article.title
        holder.productQuantity.text =article.quantity.toString()
        holder.productPrice.text = "$${article.price.toInt() * article.quantity}"
        holder.increaseButton.setOnClickListener {

            val db = DBHelper(context, null)
            val name = article.title
            val age = article.price

//            val cursor = db.getName()
//            cursor!!.moveToFirst()
//            var cartTitleList = arrayListOf<String>()
//            cartTitleList.add(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)))
//            while (cursor.moveToNext()) {
//                cartTitleList.add(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)))
//            }
//            var id = (cartTitleList.size + 1).toString()

            db.addQuantity(cartList, article.title, article.price, article.quantity.toString())
            quantity++
            holder.productQuantity.text = quantity.toString()
            holder.productPrice.text = "$${article.price.toInt() * quantity}"
            notifyDataSetChanged()
        }
        holder.decreaseButton.setOnClickListener {

            val db = DBHelper(context, null)
            val name = article.title
            val age = article.price

            // calling method to add
            // name to our database
            db.onDelete(cartList, article.title)
            quantity--
            holder.productQuantity.text = quantity.toString()
            holder.productPrice.text = "$${article.price.toInt() * quantity}"
            notifyDataSetChanged()
        }
//        holder.productPrice.text = "$${articles.price}"
//        holder.productPrice.showStrikeThrough(true)
//        Glide.with(context).load(articles.thumbnail).into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}