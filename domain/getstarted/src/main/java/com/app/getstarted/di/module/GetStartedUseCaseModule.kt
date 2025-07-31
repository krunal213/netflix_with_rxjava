package com.app.getstarted.di.module

import com.app.getstarted.usecase.GetStartedUseCase
import com.app.getstarted.usecase.GetStartedUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class GetStartedUseCaseModule {

    @Binds
    abstract fun bindGetStartedUseCase(getStartedUseCaseImpl: GetStartedUseCaseImpl): GetStartedUseCase
}