package com.app.getstarted.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.common.NetflixApplication
import com.app.getstarted.usecase.LoginUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(application: NetflixApplication, val loginUseCase: LoginUseCase) :
    AndroidViewModel(application) {

    fun login(email: String, password: String): Single<Unit> =
        loginUseCase(email, password)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())


}