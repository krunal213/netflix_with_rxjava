package com.app.getstarted.repository.datasource.firebase

import com.app.getstarted.repository.exception.NoUserFoundException
import com.app.getstarted.repository.exception.SomethingWentWrongException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import org.junit.Before
import org.junit.Test

class AuthenticationFirebaseDataSourceImplTest {

    private lateinit var authenticationFirebaseDataSourceImpl: AuthenticationFirebaseDataSourceImpl
    private lateinit var mockFirebaseAuth: FirebaseAuth

    @Before
    fun setUp() {
        mockFirebaseAuth = mockk<FirebaseAuth>()
        authenticationFirebaseDataSourceImpl =
            AuthenticationFirebaseDataSourceImpl(mockFirebaseAuth)
    }

    @Test
    fun test_login_when_login_success() {
        val mockTask = mockk<Task<AuthResult>>(relaxed = true)
        val slot = slot<OnCompleteListener<AuthResult>>()

        every { mockFirebaseAuth.signInWithEmailAndPassword(any(), any()) } returns mockTask
        every { mockTask.addOnCompleteListener(capture(slot)) } answers {
            every { mockTask.isSuccessful } returns true
            slot.captured.onComplete(mockTask)
            mockTask
        }

        val testObserver =
            authenticationFirebaseDataSourceImpl
                .login("test@example.com", "password")
                .test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue(Unit)
    }

    @Test
    fun test_login_when_login_fail() {
        val mockTask = mockk<Task<AuthResult>>(relaxed = true)
        val slot = slot<OnCompleteListener<AuthResult>>()

        every { mockFirebaseAuth.signInWithEmailAndPassword(any(), any()) } returns mockTask
        every { mockTask.addOnCompleteListener(capture(slot)) } answers {
            every { mockTask.isSuccessful } returns false
            every { mockTask.exception } returns Exception()
            slot.captured.onComplete(mockTask)
            mockTask
        }

        val testObserver =
            authenticationFirebaseDataSourceImpl
                .login("test@example.com", "password")
                .test()

        testObserver.assertError {
            it is Exception
        }
    }

    @Test
    fun test_isUserLogin_if_user_is_not_logged_in() {
        every { mockFirebaseAuth.currentUser } returns null

        val testObserver = authenticationFirebaseDataSourceImpl.isUserLogin().test()

        testObserver.assertError {
            it is NoUserFoundException
        }
    }

    @Test
    fun test_isUserLogin_if_user_is_logged_in() {
        val mockFirebaseUser = mockk<FirebaseUser>(relaxed = true)

        every { mockFirebaseAuth.currentUser } returns mockFirebaseUser

        val testObserver = authenticationFirebaseDataSourceImpl.isUserLogin().test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue(Unit)
    }

    @Test
    fun test_signout_when_signout_success() {
        every { mockFirebaseAuth.signOut() } just Runs
        every { mockFirebaseAuth.currentUser } returns null

        val testObserver = authenticationFirebaseDataSourceImpl.signout().test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue(Unit)
    }

    @Test
    fun test_signout_when_signout_failure() {
        val mockFirebaseUser = mockk<FirebaseUser>(relaxed = true)

        every { mockFirebaseAuth.signOut() } just Runs
        every { mockFirebaseAuth.currentUser } returns mockFirebaseUser

        val testObserver = authenticationFirebaseDataSourceImpl.signout().test()

        testObserver.assertError {
            it is SomethingWentWrongException
        }
    }

}