package com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.BaseModel

class RestItem : BaseModel() {
  @SerializedName("badge_counts")
  @Expose
  val badgeCounts: RestBadgeCount? = null
  @SerializedName("account_id")
  @Expose
  val accountId: Int = 0
  @SerializedName("is_employee")
  @Expose
  val isEmployee: Boolean? = null
  @SerializedName("last_modified_date")
  @Expose
  val lastModifiedDate: Int = 0
  @SerializedName("last_access_date")
  @Expose
  val lastAccessDate: Int = 0
  @SerializedName("reputation_change_year")
  @Expose
  val reputationChangeYear: Int = 0
  @SerializedName("reputation_change_quarter")
  @Expose
  val reputationChangeQuarter: Int = 0
  @SerializedName("reputation_change_month")
  @Expose
  val reputationChangeMonth: Int = 0
  @SerializedName("reputation_change_week")
  @Expose
  val reputationChangeWeek: Int = 0
  @SerializedName("reputation_change_day")
  @Expose
  val reputationChangeDay: Int = 0
  @SerializedName("reputation")
  @Expose
  val reputation: Int = 0
  @SerializedName("creation_date")
  @Expose
  val creationDate: Int = 0
  @SerializedName("user_type")
  @Expose
  val userType: String? = null
    get() = field ?: ""
  @SerializedName("user_id")
  @Expose
  val userId: Int = 0
  @SerializedName("accept_rate")
  @Expose
  val acceptRate: Int = 0
  @SerializedName("location")
  @Expose
  val location: String? = null
    get() = field ?: ""
  @SerializedName("website_url")
  @Expose
  val websiteUrl: String? = null
    get() = field ?: ""
  @SerializedName("link")
  @Expose
  val link: String? = null
    get() = field ?: ""
  @SerializedName("profile_image")
  @Expose
  val profileImage: String? = null
    get() = field ?: ""
  @SerializedName("display_name")
  @Expose
  val displayName: String? = null
    get() = field ?: ""
}