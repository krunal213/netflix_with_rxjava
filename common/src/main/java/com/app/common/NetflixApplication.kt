package com.app.common

import android.app.Application
import com.google.firebase.FirebaseApp

class NetflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this.applicationContext)

    }

}