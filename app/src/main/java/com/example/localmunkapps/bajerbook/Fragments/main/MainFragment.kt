package com.example.localmunkapps.bajerbook.Fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.localmunkapps.bajerbook.Fragments.BBFragment
import com.example.localmunkapps.bajerbook.R

class MainFragment : BBFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // setup fragment

        return view
    }

    private fun setupFragment() {



    }


}