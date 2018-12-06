package com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun requestUserStackExchange(order: String, sort: String, site: String, page: Int) =
        apiService.requestUserSE(order, sort, site, page)
}