package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.utildev.kotlin.arch.architecturecomponentskotlin.BR

class UserRemoteAdapter(
    layoutId: Int,
    recyclerView: RecyclerView,
    layoutManager: LinearLayoutManager?,
    adapterListener: BaseAdapter.AdapterListener?
) : BaseAdapter(layoutId, recyclerView, layoutManager, adapterListener) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restItem = items[position]
        holder.binding.setVariable(BR.viewModel, restItem)
        holder.binding.executePendingBindings()
    }
}