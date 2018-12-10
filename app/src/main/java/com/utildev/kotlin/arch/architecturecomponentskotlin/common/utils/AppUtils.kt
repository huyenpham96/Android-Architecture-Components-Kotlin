package com.utildev.kotlin.arch.architecturecomponentskotlin.common.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.lang.Exception

object AppUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun setupKeyboard(view: View, activity: Activity, clearFocus: Boolean) {
        if (!(view is EditText)) {
            view.setOnTouchListener { _, _ ->
                if (view.getTag() != null) {
                    return@setOnTouchListener false
                }
                if (clearFocus) {
                    view.requestFocus()
                }
                hideSoftKeyboard(activity)
                return@setOnTouchListener false
            }
        }
        if (view is ViewGroup) {
            val childCount = view.childCount
            for (i in 0 until childCount) {
                val innerView = view.getChildAt(i)
                setupKeyboard(innerView, activity, clearFocus)
            }
        }
    }

    private fun hideSoftKeyboard(activity: Activity) {
        try {
            val manager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            val view = activity.currentFocus
            if (view != null) {
                manager.hideSoftInputFromWindow(view.windowToken, 0)
                if (view is EditText) {
                    clearFocusEditText(arrayOf(view))
                }
            }
        } catch (e: Exception) {
        }
    }

    private fun clearFocusEditText(editTexts: Array<EditText>) {
        for (editText in editTexts) {
            editText.clearFocus()
        }
    }
}