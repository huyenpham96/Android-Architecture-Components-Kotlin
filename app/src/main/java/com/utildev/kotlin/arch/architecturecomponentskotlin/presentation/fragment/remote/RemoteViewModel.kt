package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.utildev.kotlin.arch.architecturecomponentskotlin.common.helper.LiveEvent
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange.RestItem
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange.RestUser
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteViewModel : BaseViewModel() {
    var userListSE: LiveEvent<MutableList<RestItem>>? = null
    var storeList: MutableList<RestItem> = ArrayList()
    var page: Int = 1

    fun getUserStackExchange(order: String, sort: String, site: String, page: Int, showLoading: Boolean) {
        if (showLoading) {
            showLoading()
        }
        if (userListSE == null) {
            userListSE = LiveEvent()
        }
        val disposable = repository.getAllUserSE(order, sort, site, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    val type = object : TypeToken<RestUser>() {}.type
                    storeList.addAll((Gson().fromJson(it, type) as RestUser).items!!)
                    userListSE!!.value = storeList
                    dismissLoading()
                }
            }, Throwable::printStackTrace)
        compositeDisposable.add(disposable)
    }
}