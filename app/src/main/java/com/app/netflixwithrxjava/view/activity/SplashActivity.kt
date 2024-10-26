package com.app.netflixwithrxjava.view.activity

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.app.netflixwithrxjava.R

class SplashActivity : AppCompatActivity(), Animator.AnimatorListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        findViewById<LottieAnimationView>(R.id.animation_view).addAnimatorListener(this)
    }

    override fun onAnimationStart(p0: Animator) {

    }

    override fun onAnimationEnd(p0: Animator) {
        startActivity(Intent(this,GetStartedActivity::class.java))
        finish()
    }

    override fun onAnimationCancel(p0: Animator) {

    }

    override fun onAnimationRepeat(p0: Animator) {
    }

}