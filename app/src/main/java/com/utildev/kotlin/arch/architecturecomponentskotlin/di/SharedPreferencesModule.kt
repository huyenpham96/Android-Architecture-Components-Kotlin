package com.utildev.kotlin.arch.architecturecomponentskotlin.di

import android.content.Context
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {
  @Provides
  @Singleton
  fun provideSharedPreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)
}