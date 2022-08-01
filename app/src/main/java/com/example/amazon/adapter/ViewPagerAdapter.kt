package com.example.amazon.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.amazon.tab_fragments.HomeFragment
import com.example.amazon.tab_fragments.ItemFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
            0->{
               HomeFragment()
            }
            1->{
                ItemFragment()
            }
            else->{
                Fragment()
            }
        }
    }
}