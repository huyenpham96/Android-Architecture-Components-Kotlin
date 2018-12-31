package com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.BaseModel

class RestUser : BaseModel() {
    @SerializedName("items")
    @Expose
    val items: MutableList<RestItem>? = null
    @SerializedName("has_more")
    @Expose
    val hasMore: Boolean? = null
    @SerializedName("quota_max")
    @Expose
    val quotaMax: Int = 0
    @SerializedName("quota_remaining")
    @Expose
    val quotaRemaining: Int = 0
}