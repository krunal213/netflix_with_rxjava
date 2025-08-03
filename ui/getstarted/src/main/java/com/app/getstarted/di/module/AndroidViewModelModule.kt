package com.app.getstarted.di.module

import androidx.lifecycle.AndroidViewModel
import com.app.getstarted.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class AndroidViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): AndroidViewModel
}