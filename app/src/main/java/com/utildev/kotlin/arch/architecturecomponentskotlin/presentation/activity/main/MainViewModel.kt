package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.main

import android.content.ContextWrapper
import android.view.View
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.BaseViewModel
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.BaseActivity
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.remote.RemoteFragment
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.room.RoomFragment

class MainViewModel : BaseViewModel() {
    fun onCLickRemote(view: View?) {
        if (view!!.context is BaseActivity) {
            (view.context as BaseActivity).replaceFragment(RemoteFragment(), true, true)
        } else if (view.context is ContextWrapper) {
            ((view.context as ContextWrapper).baseContext as BaseActivity)
                .addFragment(RemoteFragment(), true, true)
        }
    }

    fun onClickRoom(view: View?) {
        if (view!!.context is BaseActivity) {
            (view.context as BaseActivity).replaceFragment(RoomFragment(), true, true)
        } else if (view.context is ContextWrapper) {
            ((view.context as ContextWrapper).baseContext as BaseActivity)
                .addFragment(RoomFragment(), true, true)
        }
    }

    fun onClickGithub(view: View?) {

    }
}