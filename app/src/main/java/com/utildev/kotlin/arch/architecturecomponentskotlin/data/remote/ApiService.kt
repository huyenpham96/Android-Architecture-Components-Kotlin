package com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote

import com.google.gson.JsonObject
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.BaseModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @GET("users")
    fun requestUserSE(
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String,
        @Query("page") page: Int
    ): Observable<JsonObject>

    @FormUrlEncoded
    @POST("key, object")
    fun requestNormal(@FieldMap body: Map<String, Any>): Observable<JsonObject>

    @POST("list")
    fun requestList(@Body list: List<BaseModel>): Observable<JsonObject>

    @GET("not params")
    fun requestNotParams(): Observable<JsonObject>

    @POST("upload file")
    fun requestFile(@Body file: RequestBody): Observable<JsonObject>
}