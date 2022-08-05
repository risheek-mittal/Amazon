package com.example.amazon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.example.amazon.R
import com.example.amazon.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

//    private var imageArray:ArrayList<Int> = ArrayList()
//    private var carouselView2:CarouselView? =null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        imageArray.add(R.drawable.one)
//        imageArray.add(R.drawable.two)
//        imageArray.add(R.drawable.three)
//        imageArray.add(R.drawable.four)
        requireActivity().setTitle("Home")
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val tabLayout=view.findViewById<TabLayout>(R.id.tabLayout)

        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2)

        val adapter = ViewPagerAdapter((activity as AppCompatActivity).supportFragmentManager, lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2){tab, position->
            when(position){
                0->{
                    tab.text="Home"
                }
                1->{
                    tab.text="Items"
                }
            }
        }.attach()

//        carouselView2 = carouselView
//        carouselView2!!.pageCount = imageArray.size
//        carouselView2!!.setImageListener(imageListener)

        return view
    }

//    var imageListener = ImageListener { position, imageView -> imageView.setImageResource(imageArray[position])}
}