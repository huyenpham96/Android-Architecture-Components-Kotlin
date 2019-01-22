package com.utildev.kotlin.arch.architecturecomponentskotlin.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.utildev.kotlin.arch.architecturecomponentskotlin.common.extensions.DB_NAME
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class RoomUserDataSource : RoomDatabase() {
  abstract fun userDao(): RoomUserDao

  companion object {
    fun createUserDB(context: Context): RoomUserDataSource =
      Room.databaseBuilder(context, RoomUserDataSource::class.java, DB_NAME).build()
  }
}