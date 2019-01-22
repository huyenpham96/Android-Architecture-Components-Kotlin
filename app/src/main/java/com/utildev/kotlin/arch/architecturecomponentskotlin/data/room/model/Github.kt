package com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model

import com.utildev.kotlin.arch.architecturecomponentskotlin.data.BaseModel

class Github(private val title: String?, private val lib: String?, private val github: String?) : BaseModel() {
  fun getTitle(): String? = title ?: ""
  fun getLib(): String? = lib ?: ""
  fun getGithub(): String? = github ?: ""
}