package com.utildev.kotlin.arch.architecturecomponentskotlin.di

import android.app.Application

class MyApplication : Application() {
  companion object {
    lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.builder()
      .appModule(AppModule(this))
      .remoteModule(RemoteModule())
      .roomModule(RoomModule())
      .sharedPreferencesModule(SharedPreferencesModule())
      .build()
  }
}