package com.app.getstarted.di.module

import com.app.getstarted.repository.GetStartedRepository
import com.app.getstarted.repository.GetStartedRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindGetStartedRepository(getStartedRepositoryImpl: GetStartedRepositoryImpl): GetStartedRepository
}