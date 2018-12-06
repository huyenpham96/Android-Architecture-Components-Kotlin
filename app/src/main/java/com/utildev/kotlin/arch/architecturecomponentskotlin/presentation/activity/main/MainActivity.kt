package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.main

import android.os.Bundle
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
