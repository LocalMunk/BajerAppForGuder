package com.example.localmunkapps.bajerbook.Activities.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import com.example.localmunkapps.bajerbook.Activities.BBActivity
import com.example.localmunkapps.bajerbook.Activities.main.MainActivity
import com.example.localmunkapps.bajerbook.Fragments.signup.SignUpFragment
import com.example.localmunkapps.bajerbook.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BBActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        animateLogin()
    }

    private fun animateLogin() {

        val animatorSet = AnimatorSet()

        // login container animation
        val startPosition = screenDimensions.heightPixels - loginInformationContainer.height.toFloat()
        val endPosition = 0f
        val transitionAnimation = ObjectAnimator.ofFloat(loginInformationContainer, View.TRANSLATION_Y, startPosition, endPosition)
        transitionAnimation.interpolator = FastOutSlowInInterpolator()
        transitionAnimation.duration = 1000

        // logo animation
        val logoAnimationDuration = 1500L

        val logoAlphaAnimation = ObjectAnimator.ofFloat(loginLogo, View.ALPHA, 0f, 1f)
        logoAlphaAnimation.duration = logoAnimationDuration

        val logoTransitionAnimation = ObjectAnimator.ofFloat(loginLogo, View.TRANSLATION_Y, -200f, 0f)
        logoTransitionAnimation.duration = logoAnimationDuration

        // play animations parallel
        animatorSet.playTogether(transitionAnimation, logoAlphaAnimation, logoTransitionAnimation)
        animatorSet.start()
    }

    // login onClick
    fun loginClick(v: View) {

        // add login logic

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    // sign up onClick
    fun signUpClick (v: View) {
        val fragment = SignUpFragment()
        navigateToFragment(fragment, null, fragment.TAG)
    }

// test margin er lol



}
