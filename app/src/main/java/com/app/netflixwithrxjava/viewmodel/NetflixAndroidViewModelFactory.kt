package com.app.netflixwithrxjava.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.netflixwithrxjava.NetflixApplication
import javax.inject.Inject

class NetflixAndroidViewModelFactory @Inject constructor(
    netflixApplication: NetflixApplication,
    val viewmodelClasses: Map<Class<*>, @JvmSuppressWildcards AndroidViewModel>
) : ViewModelProvider.AndroidViewModelFactory(netflixApplication) {

    override fun <T : ViewModel> create(modelClass: Class<T>) = viewmodelClasses[modelClass] as T

}