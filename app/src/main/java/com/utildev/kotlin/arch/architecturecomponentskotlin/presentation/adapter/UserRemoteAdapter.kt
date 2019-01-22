package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.utildev.kotlin.arch.architecturecomponentskotlin.BR
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.android.synthetic.main.view_loadmore.view.*
import java.lang.Exception

class UserRemoteAdapter(
  layoutId: Int,
  recyclerView: RecyclerView,
  layoutManager: LinearLayoutManager?,
  adapterListener: BaseAdapter.AdapterListener?
) : BaseAdapter(layoutId, recyclerView, layoutManager, adapterListener) {

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    try {
      val restItem = items[position]
      holder.binding.setVariable(BR.viewModel, restItem)
      holder.binding.executePendingBindings()
      val view = holder.binding.root
      if (position == items.size - 1) {
        view.itemUser_decorator.visibility = View.GONE
      } else {
        view.itemUser_decorator.visibility = View.VISIBLE
      }
    } catch (e: Exception) {
      holder.binding.root.viewLoadMore.visibility = if (isEndList) View.GONE else View.VISIBLE
    }
  }
}