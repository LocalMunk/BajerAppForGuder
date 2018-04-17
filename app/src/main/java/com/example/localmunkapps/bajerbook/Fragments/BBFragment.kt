package com.example.localmunkapps.bajerbook.Fragments

import android.support.v4.app.Fragment

abstract class BBFragment : Fragment() {

    open fun handlesOnBackPressed(): Boolean {
        return false
    }
}