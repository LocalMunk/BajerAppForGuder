package com.example.localmunkapps.bajerbook.Activities

import android.support.v7.app.AppCompatActivity
import com.example.localmunkapps.bajerbook.Fragments.BBFragment

abstract class BBActivity : AppCompatActivity() {

    override fun onBackPressed() {

        val fragments = supportFragmentManager.fragments

        if (fragments.isEmpty()) {
            return super.onBackPressed()
        }

        val currentFrag = fragments[fragments.size - 1]

        if (currentFrag is BBFragment && currentFrag.handlesOnBackPressed()) {
            return
        }
    }

}