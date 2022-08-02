package com.example.amazon.tab_fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazon.R
import com.example.amazon.adapter.ProductAdapter
import com.example.amazon.fragments.ItemDetailsFragment
import com.example.amazon.interfaces.ModelApi
import com.example.amazon.models.ApiModel
import com.example.amazon.models.Retro
import kotlinx.android.synthetic.main.fragment_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemFragment : Fragment() {

    lateinit var adapter: ProductAdapter
    lateinit var product: List<ApiModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var thisContext = requireActivity().applicationContext
        getQuote(thisContext)
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    private fun getQuote(context: Context) {
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
                product = response!!.body()!!
//                val list = response.
                adapter = ProductAdapter(context, product)
                productList.adapter = adapter
                productList.layoutManager = LinearLayoutManager(context)

                adapter.setOnItemClickListener(object : ProductAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
//                        val intent = Intent(requireActivity(), ItemDetailsFragment::class.java)
//                        requireActivity().startActivity(intent)
//                        requireActivity().run{
//                            startActivity(Intent(this, ItemDetailsFragment::class.java))
//                            finish()
                        activity!!
                            .supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.mainContainer, ItemDetailsFragment())
                            .commitNow()
//                        }
//                        val itemDetailsFragment = ItemDetailsFragment()
//                        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
//                        transaction.replace(R.id.mainContainer, itemDetailsFragment)
//                        transaction.commit()
                    }

                })
            }

            override fun onFailure(call: Call<List<ApiModel>>, t: Throwable) {
                Log.e("Fail", t.toString())
            }

        })
    }

}