package com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote

class RemoteDataSource(private val apiService: ApiService) {
    fun requestUserStackExchange(order: String, sort: String, site: String, page: Int) =
        apiService.requestUserSE(order, sort, site, page)
}