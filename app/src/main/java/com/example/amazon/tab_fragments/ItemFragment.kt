package com.example.amazon.tab_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amazon.R
import com.example.amazon.interfaces.ModelApi
import com.example.amazon.models.ApiModel
import com.example.amazon.models.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class ItemFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getQuote()
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    private fun getQuote() {
        val retro = Retro().getRetroClient().create(ModelApi::class.java)
        retro.getQuote().enqueue(object : Callback<List<ApiModel>>{
            override fun onResponse(
                call: Call<List<ApiModel>>,
                response: Response<List<ApiModel>>
            ) {
                Log.d("Return", response.body()!!.toString())
                for(q in response.body()!!){
                    Log.e("Wow", q.title.toString())
                }
//                val list = response.
            }

            override fun onFailure(call: Call<List<ApiModel>>, t: Throwable) {
                Log.e("Fail", t.toString())
            }

        })
    }

}