package com.app.netflixwithrxjava.di.module

import com.app.netflixwithrxjava.repository.datasource.network.NetflixService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetflixNetworkModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory() = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory) = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @Singleton
    fun provideNetflixService(retrofit: Retrofit) = retrofit.create(NetflixService::class.java)
}