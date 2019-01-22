package com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.BaseModel

class RestBadgeCount : BaseModel() {
  @SerializedName("bronze")
  @Expose
  val bronze: Int = 0
  @SerializedName("silver")
  @Expose
  val silver: Int = 0
  @SerializedName("gold")
  @Expose
  val gold: Int = 0
}