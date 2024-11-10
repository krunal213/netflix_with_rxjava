package com.app.netflixwithrxjava.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.widget.ViewPager2
import com.app.netflixwithrxjava.R
import it.xabaras.android.viewpagerindicator.widget.ViewPagerIndicator2

class GetStartedFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_getting_started,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        view.findViewById<ViewPager2>(R.id.viewpager).apply {
            setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
            adapter = ScreenSlidePagerAdapter(childFragmentManager,lifecycle)
            view.findViewById<ViewPagerIndicator2>(R.id.viewPagerIndicator).initWithViewPager(this)
        }
        view.findViewById<Button>(R.id.button).setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_get_started, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button->{
                findNavController().navigate(R.id.action_getStartedFragment_to_emailVerificationFragment)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean{
        if(item.itemId==R.id.action_loginFragment){
            findNavController().navigate(R.id.action_loginFragment)
            return super.onOptionsItemSelected(item)
        }else{
            val onOptionsItemSelected = NavigationUI.onNavDestinationSelected(
                item,
                findNavController()
            ) || super.onOptionsItemSelected(item)
            if (item.itemId==R.id.helpActivity){
                requireActivity().overridePendingTransition(
                    R.anim.animate_in_out_enter,
                    R.anim.animate_in_out_exit
                )
            }
            return onOptionsItemSelected
        }
    }
}