package com.app.netflixwithrxjava.view.fragment

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScreenSlidePagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int) = when (position) {
        0 -> FirstFragment()
        1 -> SecondFragment()
        2 -> ThirdFragment()
        3 -> FourthFragment()
        else -> throw Exception()
    }
}