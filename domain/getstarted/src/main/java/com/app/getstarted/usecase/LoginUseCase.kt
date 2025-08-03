package com.app.getstarted.usecase

import com.app.getstarted.repository.GetStartedRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginUseCase @Inject constructor(val getStartedRepository: GetStartedRepository) {

    operator fun invoke(email: String, password: String): Single<Unit> {
        return getStartedRepository.login(email, password)
    }
}