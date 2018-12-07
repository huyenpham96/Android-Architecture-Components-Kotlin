package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.BR
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.UserEntity

class UserRoomAdapter(
    layoutId: Int,
    recyclerView: RecyclerView,
    layoutManager: LinearLayoutManager?,
    adapterListener: BaseAdapter.AdapterListener?
) : BaseAdapter(layoutId, recyclerView, layoutManager, adapterListener) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemRoom = items[position]
        holder.binding.setVariable(BR.viewModel, itemRoom)
        holder.binding.executePendingBindings()
    }
}