package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.common.MySharedPreferences
import com.utildev.kotlin.arch.architecturecomponentskotlin.di.MyApplication
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment
import javax.inject.Inject

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var mySharedPreferences: MySharedPreferences

    init {
        MyApplication.appComponent.inject(this)
    }

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
                    R.anim.activity_new_in, R.anim.activity_old_out,
                    R.anim.activity_old_in, R.anim.activity_new_out
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