package com.app.getstarted.repository

import io.reactivex.rxjava3.core.Single

interface GetStartedRepository {

    fun login(email: String, password: String): Single<Unit>

}