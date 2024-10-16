package com.app.netflixwithrxjava.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.netflixwithrxjava.R

class HomeFragment : DownloadSearchMenuFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_home,container,false)

}