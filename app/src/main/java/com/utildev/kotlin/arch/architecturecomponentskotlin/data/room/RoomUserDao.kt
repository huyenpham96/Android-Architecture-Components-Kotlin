package com.utildev.kotlin.arch.architecturecomponentskotlin.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.utildev.kotlin.arch.architecturecomponentskotlin.common.extensions.TABLE_NAME
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.UserEntity
import io.reactivex.Flowable

@Dao
interface RoomUserDao {
  @Query("SELECT COUNT(*) FROM $TABLE_NAME")
  fun getUserCount(): Flowable<Int>

  @Query("SELECT * FROM $TABLE_NAME")
  fun getAllUser(): Flowable<List<UserEntity>>

  @Insert
  fun insertUser(userEntity: UserEntity)

  @Delete
  fun deleteUser(userEntity: UserEntity)
}