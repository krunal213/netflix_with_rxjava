package com.app.getstarted.repository.datasource.firebase

import io.reactivex.rxjava3.core.Single

interface AuthenticationFirebaseDataSource {

    fun login(email: String, password: String): Single<Unit>

    fun isUserLogin(): Single<Unit>

    fun signout(): Single<Unit>
}