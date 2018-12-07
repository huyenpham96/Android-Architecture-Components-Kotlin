package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.remote

import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange.RestUser
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RemoteViewModel : BaseViewModel() {
    var userListSE: MutableLiveData<RestUser>? = null

    fun getUserStackExchange(order: String, sort: String, site: String, page: Int, showLoading: Boolean) {
        if (showLoading) {
            showLoading(null)
        }
        if (userListSE == null) {
            userListSE = MutableLiveData()
        }
        val disposable = repository.getAllUserSE(order, sort, site, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    val type = object : TypeToken<RestUser>() {}.type
                    userListSE!!.value = Gson().fromJson(it, type)
                    dismissLoading(null)
                }
            }, Throwable::printStackTrace)
        compositeDisposable.add(disposable)
    }
}