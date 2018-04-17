package com.example.localmunkapps.bajerbook.Activities.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.localmunkapps.bajerbook.Activities.BBActivity
import com.example.localmunkapps.bajerbook.Activities.main.MainActivity
import com.example.localmunkapps.bajerbook.Fragments.SignUpFragment
import com.example.localmunkapps.bajerbook.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BBActivity() {

    val screenDimensions: DisplayMetrics
        get() {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics
        }

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

    // login onClick
    fun loginClick(v: View) {

        // add login logic

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    // sign up onClick
    fun signUpClick (v: View) {
        val fragment = SignUpFragment()
        navigateToFragment(fragment, null, fragment.TAG, false)
    }

    fun navigateToFragment(fragment: Fragment, argument: Bundle?, backstackName: String?, animate: Boolean) {
        fragment.arguments = argument
        val supFragMan = supportFragmentManager.beginTransaction()
//        if (animate) {
//            supFragMan.setCustomAnimations(R.anim.slide_in_left,
//                    R.anim.slide_out_left,
//                    R.anim.slide_in_right,
//                    R.anim.slide_out_right)
//        }
        supFragMan.add(loginContainer.id, fragment, backstackName)
        if (backstackName != null) {
            supFragMan.addToBackStack(backstackName)
        }
        supFragMan.commit()
        hideKeyboard(this)
    }

    fun hideKeyboard(activity: Activity) {
        if (activity.currentFocus == null) return

        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
    }

}
