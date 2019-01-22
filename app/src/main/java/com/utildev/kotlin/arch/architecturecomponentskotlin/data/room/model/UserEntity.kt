package com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.utildev.kotlin.arch.architecturecomponentskotlin.common.extensions.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  @ColumnInfo(name = "name")
  var name: String?,
  @ColumnInfo(name = "job")
  var job: String?
)