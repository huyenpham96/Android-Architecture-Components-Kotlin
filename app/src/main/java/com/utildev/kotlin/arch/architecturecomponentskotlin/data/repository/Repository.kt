package com.utildev.kotlin.arch.architecturecomponentskotlin.data.repository

import com.google.gson.JsonObject
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.UserEntity
import io.reactivex.Flowable
import io.reactivex.Observable

interface Repository {
    fun getAllUserSE(order: String, sort: String, site: String, page: Int): Observable<JsonObject>

    fun getUserCount(): Flowable<Int>

    fun getAllUser(): Flowable<List<UserEntity>>

    fun insertUser(userEntity: UserEntity)

    fun deleteUser(userEntity: UserEntity)
}