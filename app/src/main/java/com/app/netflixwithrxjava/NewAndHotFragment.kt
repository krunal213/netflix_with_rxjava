package com.app.netflixwithrxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

class NewAndHotFragment : DownloadSearchMenuFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_new_and_hot,container,false)
}