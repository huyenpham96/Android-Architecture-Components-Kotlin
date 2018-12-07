package com.utildev.kotlin.arch.architecturecomponentskotlin.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object AppUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}