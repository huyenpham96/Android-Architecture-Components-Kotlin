package com.utildev.kotlin.arch.architecturecomponentskotlin.di

import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.BaseViewModel
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RemoteModule::class, RoomModule::class, SharedPreferencesModule::class])
@Singleton
interface AppComponent {
  fun inject(baseViewModel: BaseViewModel)
  fun inject(baseActivity: BaseActivity)
}