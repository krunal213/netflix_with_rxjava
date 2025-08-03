package com.app.getstarted.di.module

import com.app.getstarted.repository.datasource.firebase.AuthenticationFirebaseDataSource
import com.app.getstarted.repository.datasource.firebase.AuthenticationFirebaseDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindAuthenticationFirebaseDataSource(authenticationFirebaseDataSourceImpl: AuthenticationFirebaseDataSourceImpl): AuthenticationFirebaseDataSource
}