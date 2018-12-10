package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity

import android.support.v7.app.AppCompatActivity
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment

open class BaseActivity : AppCompatActivity() {
    private fun transactionFragment(
        fragment: BaseFragment,
        replace: Boolean,
        addToBackStack: Boolean,
        animation: Boolean
    ) {
        if (supportFragmentManager != null) {
            val fmTransaction = supportFragmentManager.beginTransaction()
            if (animation) {
                fmTransaction.setCustomAnimations(
                    R.anim.slide_enter_from_right, R.anim.slide_exit_to_left,
                    R.anim.slide_enter_from_left, R.anim.slide_exit_to_right
                )
            } else {
                fmTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            if (replace) {
                fmTransaction.replace(R.id.flContainer, fragment, fragment::class.java.simpleName)
            } else {
                val currentFm = supportFragmentManager.findFragmentById(R.id.flContainer) as BaseFragment?
                if (currentFm != null) {
                    fmTransaction.hide(currentFm)
                }
                fmTransaction.add(R.id.flContainer, fragment, fragment::class.java.simpleName)
            }
            if (addToBackStack) {
                fmTransaction.addToBackStack(fragment::class.java.simpleName)
            }
            fmTransaction.commit()
        }
    }

    fun replaceFragment(fragment: BaseFragment, addToBackStack: Boolean, animation: Boolean) =
        transactionFragment(fragment, true, addToBackStack, animation)

    fun addFragment(fragment: BaseFragment, addToBackStack: Boolean, animation: Boolean) =
        transactionFragment(fragment, false, addToBackStack, animation)

    fun clearAllStack() {
        val fmCount = supportFragmentManager.backStackEntryCount
        for (i in 0..fmCount) {
            supportFragmentManager.popBackStack()
        }
    }

    fun clearStack() {
        supportFragmentManager.popBackStack()
    }
}