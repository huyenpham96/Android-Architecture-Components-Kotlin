package com.utildev.kotlin.arch.architecturecomponentskotlin.di

import android.content.Context
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.RoomUserDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
  @Provides
  @Singleton
  fun provideRoomDataSource(context: Context) = RoomUserDataSource.createUserDB(context)
}