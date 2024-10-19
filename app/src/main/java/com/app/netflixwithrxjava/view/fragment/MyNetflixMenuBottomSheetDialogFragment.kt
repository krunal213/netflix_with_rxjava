package com.app.netflixwithrxjava.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.netflixwithrxjava.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyNetflixMenuBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog_fragment_my_netflix,container,false)
    }

}