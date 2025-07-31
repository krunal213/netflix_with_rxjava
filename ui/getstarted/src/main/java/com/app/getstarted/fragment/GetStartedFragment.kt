package com.app.getstarted.fragment

import android.content.Intent
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
import com.app.getstarted.R
import com.app.getstarted.adapter.ScreenSlidePagerAdapter
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
        return when(item.itemId){
            R.id.action_loginFragment->{
                findNavController().navigate(R.id.action_loginFragment)
                super.onOptionsItemSelected(item)
            }
            R.id.helpActivity->{
                startActivity(Intent().apply {
                    setClassName(requireActivity(),"com.app.help.activity.HelpActivity")
                })
                requireActivity().overridePendingTransition(
                    R.anim.animate_in_out_enter,
                    R.anim.animate_in_out_exit
                )
                super.onOptionsItemSelected(item)
            }
            else->{
                NavigationUI.onNavDestinationSelected(
                    item,
                    findNavController()
                ) || super.onOptionsItemSelected(item)
            }
        }
    }
}