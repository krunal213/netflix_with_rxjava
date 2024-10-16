package com.app.netflixwithrxjava.di.module

import android.app.Application
import androidx.room.Room
//import com.app.netflixwithrxjava.repository.datasource.database.NetflixRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetflixDatabaseModule {

    /*@Provides
    @Singleton
    fun provideNetflixRoomDatabase(application: Application) = Room
        .databaseBuilder(
            application,
            NetflixRoomDatabase::class.java,
            NetflixRoomDatabase::class.java.simpleName
        ).build()

    @Provides
    @Singleton
    fun provideNetflixDao(netflixRoomDatabase: NetflixRoomDatabase) =
        netflixRoomDatabase.netflixDao()*/

}