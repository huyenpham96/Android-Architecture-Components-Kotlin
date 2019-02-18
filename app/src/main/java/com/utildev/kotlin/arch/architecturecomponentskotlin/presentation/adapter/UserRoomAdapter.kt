package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.utildev.kotlin.arch.architecturecomponentskotlin.BR
import java.lang.Exception

class UserRoomAdapter(
  layoutId: Int,
  recyclerView: RecyclerView,
  layoutManager: LinearLayoutManager?,
  adapterListener: BaseAdapter.AdapterListener?
) : BaseAdapter(layoutId, recyclerView, layoutManager, adapterListener) {

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder is ItemViewHolder) {
      val itemRoom = items[position]
      holder.binding.setVariable(BR.viewModel, itemRoom)
      holder.binding.executePendingBindings()
    }
//    try {
//
//    } catch (e: Exception) {
//    }
  }
}