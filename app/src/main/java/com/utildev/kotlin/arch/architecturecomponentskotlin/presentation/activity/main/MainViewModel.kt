package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.main

import android.arch.lifecycle.MutableLiveData
import android.content.ContextWrapper
import android.view.View
import com.utildev.kotlin.arch.architecturecomponentskotlin.common.helper.LiveEvent
import com.utildev.kotlin.arch.architecturecomponentskotlin.common.helper.SingleLiveEvent
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.BaseViewModel
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.BaseActivity
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.github.GithubFragment
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.remote.RemoteFragment
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.room.RoomFragment

class MainViewModel : BaseViewModel() {
    var ob: SingleLiveEvent<Int> = SingleLiveEvent()

    fun onCLickRemote(view: View?) {
        if (view!!.context is BaseActivity) {
            (view.context as BaseActivity).replaceFragment(RemoteFragment(), true, true)
        } else if (view.context is ContextWrapper) {
            ((view.context as ContextWrapper).baseContext as BaseActivity)
                .replaceFragment(RemoteFragment(), true, true)
        }
    }

    fun onClickRoom(view: View?) {
        if (view!!.context is BaseActivity) {
            (view.context as BaseActivity).addFragment(RoomFragment(), true, true)
        } else if (view.context is ContextWrapper) {
            ((view.context as ContextWrapper).baseContext as BaseActivity)
                .replaceFragment(RoomFragment(), true, true)
        }
    }

    fun onClickGithub(view: View?) {
//        if (view!!.context is BaseActivity) {
//            (view.context as BaseActivity).replaceFragment(GithubFragment(), true, true)
//        } else if (view.context is ContextWrapper) {
//            ((view.context as ContextWrapper).baseContext as BaseActivity)
//                .replaceFragment(GithubFragment(), true, true)
//        }
        ob.value = 1
    }
}