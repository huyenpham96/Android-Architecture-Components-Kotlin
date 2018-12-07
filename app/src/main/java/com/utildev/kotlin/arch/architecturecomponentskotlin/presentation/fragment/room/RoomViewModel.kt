package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.room

import android.arch.lifecycle.MutableLiveData
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.UserEntity
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class RoomViewModel : BaseViewModel() {
    var userLiveData: MutableLiveData<List<UserEntity>>? = null
    var userCount: MutableLiveData<Int>? = null

    fun getAllUser() {
        if (userLiveData == null) {
            userLiveData = MutableLiveData()
        }
        val disposable = repository.getAllUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    userLiveData!!.value = it
                }
            }, Throwable::printStackTrace)
        compositeDisposable.add(disposable)
    }

    fun insertUser(userEntity: UserEntity) {
        val disposable = Observable.create<Any> { emitter ->
            repository.insertUser(userEntity)
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ }, { t: Throwable? -> t?.printStackTrace() }, { Action { getAllUser() } })
        compositeDisposable.add(disposable)
    }

    fun deleteUser(userEntity: UserEntity) {
        val disposable = Observable.create<Any> { emitter ->
            repository.deleteUser(userEntity)
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { t: Throwable? -> t?.printStackTrace() }, { Action { getAllUser() } })
        compositeDisposable.add(disposable)
    }

    fun getUserCount() {
        if (userCount == null) {
            userCount = MutableLiveData()
        }
        val disposable = repository.getUserCount()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userCount!!.value = it
            }, Throwable::printStackTrace)
        compositeDisposable.add(disposable)
    }
}