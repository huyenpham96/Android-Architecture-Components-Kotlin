package com.utildev.kotlin.arch.architecturecomponentskotlin.data.repository

import com.google.gson.JsonObject
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.RemoteDataSource
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.RoomUserDataSource
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.UserEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val roomUserDataSource: RoomUserDataSource
) : Repository {
    override fun getAllUserSE(order: String, sort: String, site: String, page: Int): Observable<JsonObject> =
        remoteDataSource.requestUserStackExchange(order, sort, site, page)

    override fun getUserCount(): Flowable<Int> =
        roomUserDataSource.userDao().getUserCount()

    override fun getAllUser(): Flowable<List<UserEntity>> =
        roomUserDataSource.userDao().getAllUser()

    override fun insertUser(userEntity: UserEntity) =
        roomUserDataSource.userDao().insertUser(userEntity)

    override fun deleteUser(userEntity: UserEntity) =
        roomUserDataSource.userDao().deleteUser(userEntity)
}