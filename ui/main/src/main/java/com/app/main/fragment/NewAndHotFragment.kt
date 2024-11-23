package com.app.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.main.R

class NewAndHotFragment : DownloadSearchMenuFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_new_and_hot,container,false)
}