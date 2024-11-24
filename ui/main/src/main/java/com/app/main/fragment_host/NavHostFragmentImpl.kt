package com.app.main.fragment_host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment

class NavHostFragmentImpl : NavHostFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentContainerView : FragmentContainerView = super.onCreateView(
            inflater,
            container,
            savedInstanceState
        ) as FragmentContainerView
        fragmentContainerView.clipToPadding = false
        fragmentContainerView.clipChildren = false
        return fragmentContainerView
    }
}