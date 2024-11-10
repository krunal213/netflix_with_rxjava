package com.app.netflixwithrxjava.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.app.netflixwithrxjava.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class EmailVerificationBottomSheetDialogFragment : BottomSheetDialogFragment(),
    View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextThemeWrapper =
            ContextThemeWrapper(activity, R.style.Theme_NetflixWithRxJava_BottomSheet_Full)
        return inflater.cloneInContext(contextThemeWrapper)
            .inflate(R.layout.bottom_sheet_dialog_fragment_email_verification, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        BottomSheetDialog(requireContext(), theme).apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            window?.attributes?.windowAnimations = R.style.BottomSheetDialogAnimation
            setOnShowListener {
                val bottomSheetDialog = it as BottomSheetDialog
                val parentLayout =
                    bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                parentLayout?.let {
                    val behaviour = BottomSheetBehavior.from(it)
                    setupFullHeight(it)
                    behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                    behaviour.isDraggable = false
                }
            }
        }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_email_verification)
        toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_close -> {
                    dismiss()
                    true
                }

                else -> false
            }
        }
        view.findViewById<Button>(R.id.button_get_started).setOnClickListener(this)
        view
            .findViewById<TextInputLayout>(R.id.text_input_layout_email)
            .boxStrokeColor = requireActivity().resources.getColor(android.R.color.holo_green_dark)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_get_started -> {
                findNavController().navigate(R.id.action_loginFragment)
            }
        }
    }
}
