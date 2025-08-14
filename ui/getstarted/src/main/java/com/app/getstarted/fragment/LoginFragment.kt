package com.app.getstarted.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.app.common.NetflixApplication
import com.app.getstarted.R
import com.app.getstarted.di.component.DaggerGetStartedComponent
import com.app.getstarted.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class LoginFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var loginViewModel: LoginViewModel
    private var text_input_edit_text_email: TextInputEditText? = null
    private var text_input_edit_text_password: TextInputEditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        view.findViewById<Button>(R.id.button_sign_in).setOnClickListener(this)
        text_input_edit_text_email = view.findViewById(R.id.text_input_edit_text_email)
        text_input_edit_text_password = view.findViewById(R.id.text_input_edit_text_password)
        DaggerGetStartedComponent
            .factory()
            .create(requireActivity().application as NetflixApplication)
            .inject(this)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create_account, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_sign_in -> {
                loginViewModel
                    .login(
                        text_input_edit_text_email?.text.toString().trim(),
                        text_input_edit_text_password?.text.toString().trim()
                    )
                    .doOnSubscribe {
                        println()
                    }
                    .doFinally {
                        println()
                    }
                    .subscribe(object : SingleObserver<Unit> {
                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onError(e: Throwable) {
                            println(e)
                        }

                        override fun onSuccess(t: Unit) {
                            startActivity(Intent().apply {
                                setClassName(
                                    requireContext(),
                                    "com.app.main.activity.NetflixMainActivity"
                                )
                            })
                            activity?.finish()
                        }

                    })
            }
        }
    }

}