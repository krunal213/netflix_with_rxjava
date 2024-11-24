package com.app.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.main.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyNetflixMenuBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog_fragment_my_netflix,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView2).setOnClickListener {
            startActivity(Intent().apply {
                setClassName(requireActivity(), "com.app.settings.activity.AppSettingsActivity")
            })
            //findNavController().navigate(R.id.action_bottomDialogSheetFragmentNetflixMenu_to_activityAppSettings)
        }
    }

}