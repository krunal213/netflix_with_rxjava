package com.app.getstarted.di.component

import com.app.common.NetflixApplication
import com.app.getstarted.di.module.AndroidViewModelModule
import com.app.getstarted.di.module.DataSourceModule
import com.app.getstarted.di.module.FirebaseModule
import com.app.getstarted.di.module.RepositoryModule
import com.app.getstarted.fragment.LoginFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AndroidViewModelModule::class,
        RepositoryModule::class,
        DataSourceModule::class,
        FirebaseModule::class
    ]
)
interface GetStartedComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: NetflixApplication) : GetStartedComponent
    }

    fun inject(loginFragment: LoginFragment)
}