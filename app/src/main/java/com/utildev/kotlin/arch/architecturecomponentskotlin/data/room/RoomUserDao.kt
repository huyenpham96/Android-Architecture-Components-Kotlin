package com.utildev.kotlin.arch.architecturecomponentskotlin.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface RoomUserDao {
    @Query("SELECT COUNT(*) FROM " + RoomConstant.TABLE_NAME)
    fun getUserCount(): Flowable<Int>

    @Query("SELECT * FROM " + RoomConstant.TABLE_NAME)
    fun getAllUser(): Flowable<List<UserEntity>>

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)
}