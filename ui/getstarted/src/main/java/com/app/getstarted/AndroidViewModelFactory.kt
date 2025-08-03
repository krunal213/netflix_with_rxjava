package com.app.getstarted

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.common.NetflixApplication
import javax.inject.Inject

class AndroidViewModelFactory @Inject constructor(
    application: NetflixApplication,
    private val map: Map<Class<*>, @JvmSuppressWildcards AndroidViewModel>
) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return map[modelClass] as T
    }

}