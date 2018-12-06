package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation

import android.arch.lifecycle.ViewModel
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.repository.AppRepository
import com.utildev.kotlin.arch.architecturecomponentskotlin.di.MyApplication
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BaseViewModel: ViewModel() {
    @Inject
    lateinit var repository: AppRepository
    private var compositeDisposable = CompositeDisposable()

    init {
        MyApplication.appComponent.inject(this)
    }
}