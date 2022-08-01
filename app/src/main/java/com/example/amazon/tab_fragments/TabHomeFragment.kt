package com.example.amazon.tab_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amazon.R
import com.example.amazon.adapter.CarouselAdapter
import com.example.amazon.databinding.ActivityMainBinding
import com.example.amazon.models.CarouselModel
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import kotlinx.android.synthetic.main.fragment_home2.*

class HomeFragment : Fragment() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view2 = inflater.inflate(R.layout.fragment_home2, container, false)
        val carouselRecyclerView2 = view2.findViewById<CarouselRecyclerview>(R.id.carouselRecyclerView)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        val imageList = ArrayList<CarouselModel>()
        imageList.add(CarouselModel(R.drawable.one, ""))
        imageList.add(CarouselModel(R.drawable.two, ""))
        imageList.add(CarouselModel(R.drawable.three, ""))
        imageList.add(CarouselModel(R.drawable.four, ""))

        val adapter = CarouselAdapter(imageList)
        binding.apply {
            carouselRecyclerView2.adapter = adapter
            carouselRecyclerView2.setFlat(true)
            carouselRecyclerView2.setAlpha(true)
            carouselRecyclerView2.setInfinite(true)
        }

        return view2
    }

}