package com.utildev.kotlin.arch.architecturecomponentskotlin.common.utils

import android.app.Activity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.lang.Exception

object KeyboardUtils {
    fun setupUI(view: View, activity: Activity, clearFocus: Boolean) {
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
            val childCount = (view as ViewGroup).childCount
            for (i in 0..childCount) {
                val innerView = (view as ViewGroup).getChildAt(i)
                setupUI(innerView, activity, clearFocus)
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