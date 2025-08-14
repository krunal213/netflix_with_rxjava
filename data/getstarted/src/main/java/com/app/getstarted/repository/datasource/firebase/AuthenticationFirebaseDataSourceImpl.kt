package com.app.getstarted.repository.datasource.firebase

import com.app.getstarted.repository.exception.NoUserFoundException
import com.app.getstarted.repository.exception.SomethingWentWrongException
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthenticationFirebaseDataSourceImpl @Inject constructor(val firebaseAuth: FirebaseAuth) :
    AuthenticationFirebaseDataSource {

    override fun login(email: String, password: String): Single<Unit> {
        return Single.create { emitter ->
            firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (!emitter.isDisposed) {
                            emitter.onSuccess(Unit)
                        }
                    } else {
                        if (!emitter.isDisposed) {
                            it.exception?.let {
                                exception -> emitter.onError(exception)
                            }
                        }
                    }
                }
        }
    }

    override fun isUserLogin(): Single<Unit> {
        return Single.create { emitter ->
            if (firebaseAuth.currentUser != null) {
                if (!emitter.isDisposed) {
                    emitter.onSuccess(Unit)
                }
            } else if (!emitter.isDisposed) {
                emitter.onError(NoUserFoundException())
            }
        }
    }

    override fun signout(): Single<Unit> {
        return Single.create { emitter ->
            firebaseAuth.signOut()
            if (firebaseAuth.currentUser == null) {
                if (!emitter.isDisposed) {
                    emitter.onSuccess(Unit)
                }
            } else if (!emitter.isDisposed) {
                emitter.onError(SomethingWentWrongException())
            }
        }
    }

}