package com.example.localmunkapps.bajerbook.Activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.localmunkapps.bajerbook.Activities.login.LoginActivity
import com.example.localmunkapps.bajerbook.Activities.main.MainActivity
import com.example.localmunkapps.bajerbook.Fragments.BBFragment
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

abstract class BBActivity : AppCompatActivity() {

    val currentFrag: Fragment?
    get() {
        val fragments = supportFragmentManager.fragments
        return if (fragments.size > 0) {
            fragments[fragments.size - 1]
        } else {
            null
        }
    }

    val screenDimensions: DisplayMetrics
        get() {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics
        }

    var mainActivity: BBActivity? = null
    get() {
        return this as? MainActivity
    }

    var loginActivity: BBActivity? = null
        get() {
            return this as? LoginActivity
        }

    fun navigateToFragment(fragment: Fragment, argument: Bundle?, backStackName: String, shouldAddToBackStack: Boolean? = true) {

        fragment.arguments = argument
        val supFragMan = supportFragmentManager.beginTransaction()

        val fragmentContainer = when (this) {
            is LoginActivity -> loginContainer.id
            is MainActivity -> mainContainer.id
            else -> {
                null
            }
        }

        fragmentContainer?.let { supFragMan.add(fragmentContainer, fragment, backStackName) }

        if (shouldAddToBackStack == true) {
            supFragMan.addToBackStack(backStackName)
        }
        supFragMan.commit()
        hideKeyboard(this)
    }

    fun makeToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard(activity: Activity) {
        if (activity.currentFocus == null) return

        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
    }

    override fun onBackPressed() {

        if (currentFrag is BBFragment && (currentFrag as? BBFragment)?.handlesOnBackPressed() == true) {
            return
        }
        return super.onBackPressed()
    }

}