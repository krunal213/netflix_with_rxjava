package com.app.netflixwithrxjava.di.component

import com.app.netflixwithrxjava.NetflixApplication
import com.app.netflixwithrxjava.di.module.NetflixDatabaseModule
import com.app.netflixwithrxjava.di.module.NetflixNetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetflixNetworkModule::class,
        NetflixDatabaseModule::class
    ]
)
interface NetflixAppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance netflixApplication: NetflixApplication) : NetflixAppComponent
    }

}