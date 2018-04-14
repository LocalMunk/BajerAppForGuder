package com.example.localmunkapps.bajerbook.Activities.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.example.localmunkapps.bajerbook.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val screenDimensions: DisplayMetrics
        get() {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupAnimation()

    }

    private fun setupAnimation() {

        val animatorSet = AnimatorSet()

        // login container animation
        val startPosition = screenDimensions.heightPixels - loginInformationContainer.height.toFloat()
        val endPosition = 0f
        val transitionAnimation = ObjectAnimator.ofFloat(loginInformationContainer, View.TRANSLATION_Y, startPosition, endPosition)
        transitionAnimation.duration = 1000

        // logo animation
        val logoAnimationDuration = 1000L
        val logoStartDelay = 200L

        val logoAlphaAnimation = ObjectAnimator.ofFloat(loginLogo, View.ALPHA, 0f, 1f)
        logoAlphaAnimation.duration = logoAnimationDuration
        logoAlphaAnimation.startDelay = logoStartDelay

        val logoTransitionAnimation = ObjectAnimator.ofFloat(loginLogo, View.TRANSLATION_Y, -200f, 0f)
        logoTransitionAnimation.duration = logoAnimationDuration
        logoTransitionAnimation.startDelay = logoStartDelay

        // play animations parallel
        animatorSet.playTogether(transitionAnimation, logoAlphaAnimation, logoTransitionAnimation)
        animatorSet.start()
    }

    fun loginClick(v: View) {
        setupAnimation()
    }

}
