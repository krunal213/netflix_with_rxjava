package com.app.netflixwithrxjava.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.app.netflixwithrxjava.R
import com.app.netflixwithrxjava.view.activity.NetflixMainActivity
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        view.findViewById<Button>(R.id.button_sign_in).setOnClickListener(this)
        val textInputLayout = view.findViewById<TextInputLayout>(R.id.text_input_layout_password)
        println(textInputLayout)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create_account, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_sign_in->{
                startActivity(Intent(activity,NetflixMainActivity::class.java))
                activity?.finish()
            }
        }
    }

}