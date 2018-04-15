package com.example.localmunkapps.bajerbook.Fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.localmunkapps.bajerbook.Activities.login.LoginActivity
import com.example.localmunkapps.bajerbook.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(), View.OnClickListener{

    val TAG = "SignUpFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onStart() {
        super.onStart()
        setup()
    }

    private fun setup() {
        animateSignUp()

        signUpActionPrimary.setOnClickListener(this)
        signUpActionSecondary.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v) {
            signUpActionPrimary -> {
                animateSignUp(false)
            }
            signUpActionSecondary -> {
                animateSignUp(false)
            }
            else -> { // nothing to do
            }
        }
    }

    private fun animateSignUp(start: Boolean = true) {
        val animationSet = AnimatorSet()

        val backgroundAlphaAnimation = ObjectAnimator.ofFloat(signUpContainer, View.ALPHA,
                if (start) 0f else 1f,
                if (start) 1f else 0f
        )
        backgroundAlphaAnimation.duration = 200

        val dialogScaleXAnimation = ObjectAnimator.ofFloat(signUpDialogContainer, View.SCALE_X,
                if (start) 0.8f else 1f,
                if (start) 1f else 0.8f)
        dialogScaleXAnimation.duration = 200
        dialogScaleXAnimation.interpolator = FastOutSlowInInterpolator()

        val dialogScaleYAnimation = ObjectAnimator.ofFloat(signUpDialogContainer, View.SCALE_Y,
                if (start) 0.8f else 1f,
                if (start) 1f else 0.8f
        )
        dialogScaleYAnimation.duration = 200
        dialogScaleYAnimation.interpolator = FastOutSlowInInterpolator()

        val dialogAlphaAnimation = ObjectAnimator.ofFloat(signUpDialogContainer, View.ALPHA,
                if (start) 0f else 1f,
                if (start) 1f else 0f)
        dialogAlphaAnimation.duration = 200
        dialogAlphaAnimation.interpolator = FastOutSlowInInterpolator()

        animationSet.playTogether(backgroundAlphaAnimation, dialogAlphaAnimation, dialogScaleXAnimation, dialogScaleYAnimation)
        animationSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (!start) {
                    (context as? LoginActivity)?.supportFragmentManager?.popBackStack()
                }
            }
        })
        animationSet.start()
    }

}