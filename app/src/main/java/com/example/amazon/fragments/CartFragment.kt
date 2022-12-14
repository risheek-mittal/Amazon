package com.example.amazon.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazon.database.DBHelper
import com.example.amazon.R
import com.example.amazon.adapter.CartAdapter
import com.example.amazon.models.CartModel
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlin.collections.ArrayList


class CartFragment : Fragment() {

    private lateinit var cartTitleList : ArrayList<String>
    private lateinit var cartPriceList : ArrayList<String>
    private lateinit var cartQuantityList : ArrayList<String>
    lateinit var adapter: CartAdapter
//    lateinit var product: ArrayList<CartModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        val thisContext = container!!.context
        val textViewText = requireActivity()!!.getSharedPreferences("shopping_cart", Context.MODE_PRIVATE)
            .getString("cart_latest_item", "default value")
//        view.cartTextView.text = textViewText

        val prefs: SharedPreferences = requireActivity()!!.getSharedPreferences("PACKAGE", Context.MODE_PRIVATE)
        val serialized = prefs.getString("phrases", "Brian")
        var listOfFavoritePhrases = ArrayList<String>(listOf(TextUtils.split(serialized, ",").toString()))
        var totalPrice = 0

//        printName.setOnClickListener {

            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(thisContext, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            if(cursor.count>0){
                cartTitleList = arrayListOf<String>()
                cartPriceList = arrayListOf<String>()
                cartQuantityList = arrayListOf<String>()
                cartTitleList.add(cursor.getString(cursor.getColumnIndex(DBHelper.TITLE_COL)))
                cartPriceList.add(cursor.getString(cursor.getColumnIndex(DBHelper.PRICE_COL)))
                cartQuantityList.add(cursor.getString(cursor.getColumnIndex(DBHelper.QUANTITY_COL)))
//        print("ajcajcds")
//        print("cart list: $cartTitleList")

//        var cartTextView = view.findViewById<TextView>(R.id.cartTitleView)
//            cartTextView.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
//            view.cartPriceView.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

                // moving our cursor to next
                // position and appending values
                while (cursor.moveToNext()) {
                    cartTitleList.add(cursor.getString(cursor.getColumnIndex(DBHelper.TITLE_COL)))
                    cartPriceList.add(cursor.getString(cursor.getColumnIndex(DBHelper.PRICE_COL)))
                    cartQuantityList.add(cursor.getString(cursor.getColumnIndex(DBHelper.QUANTITY_COL)))
//                cartTextView.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
//                view.cartPriceView.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

                }
                println(cartTitleList.groupingBy { it }.eachCount().filter { it.value > 1 })
                val product = ArrayList<CartModel>()
//        product.add(CartModel("abcd"))
                for (i in 0..(cartTitleList.size - 1)) {
//                var quantity = Collections.frequency(cartTitleList, item)

                    totalPrice =
                        totalPrice + (cartPriceList[i].toInt() * cartQuantityList[i].toInt())

                    product.add(
                        CartModel(
                            cartTitleList[i],
                            cartQuantityList[i].toInt(),
                            cartPriceList[i],
                            totalPrice
                        )
                    )
//            cartTextView.append(item + ": " + Collections.frequency(cartTitleList, item) + "\n")
                }
                Log.d("Print", cartTitleList.toString())

                // at last we close our cursor
                cursor.close()
//        view.deleteButton.setOnClickListener {
//            db.onDelete("iPhone X")
//        }
                adapter = CartAdapter(thisContext, product)
                view.cartRecyclerView.adapter = adapter
                view.cartTotal.text = "Cart Value: $$totalPrice"
                view.cartRecyclerView.layoutManager = LinearLayoutManager(thisContext)
            }
        else{
            view.cartTotal.text=="$0"
            }

        return view
    }

    fun setCartTotal(view: View, totalPrice: String){
        view.cartTotal.text = "Cart Value: $$totalPrice"
    }
}