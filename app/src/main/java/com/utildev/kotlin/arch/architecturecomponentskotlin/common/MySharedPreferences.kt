package com.utildev.kotlin.arch.architecturecomponentskotlin.common

import android.content.SharedPreferences
import javax.inject.Inject

class MySharedPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun putString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String) = sharedPreferences.getString(key, "")
}