package com.app.getstarted.repository

import com.app.getstarted.repository.datasource.firebase.AuthenticationFirebaseDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetStartedRepositoryImpl @Inject constructor(val authenticationFirebaseDataSource: AuthenticationFirebaseDataSource) :
    GetStartedRepository {

    override fun login(email: String, password: String): Single<Unit> {
        return authenticationFirebaseDataSource.login(email, password)
    }

}