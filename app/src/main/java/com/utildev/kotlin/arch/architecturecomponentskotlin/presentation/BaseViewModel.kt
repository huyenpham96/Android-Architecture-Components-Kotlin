package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableInt
import android.view.View
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.repository.AppRepository
import com.utildev.kotlin.arch.architecturecomponentskotlin.di.MyApplication
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel: ViewModel() {
    @Inject
    lateinit var repository: AppRepository
    var compositeDisposable = CompositeDisposable()

    val loadingView: ObservableInt = ObservableInt(View.GONE)

    init {
        MyApplication.appComponent.inject(this)
    }

    fun showLoading(view: View?) {
        if (loadingView.get() != View.VISIBLE) {
            loadingView.set(View.VISIBLE)
        }
    }

    fun dismissLoading(view: View?) {
        if (loadingView.get() != View.GONE) {
            loadingView.set(View.GONE)
        }
    }
}