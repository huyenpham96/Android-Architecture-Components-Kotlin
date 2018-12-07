package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.BR
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.UserEntity

class UserRoomAdapter : RecyclerView.Adapter<UserRoomAdapter.ViewHolder>() {
    private var items: List<UserEntity> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_room, viewGroup, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: UserRoomAdapter.ViewHolder, position: Int) {
        val userEntity = items[position]
        holder.bind(userEntity)
    }

    fun setItems(userEntities: List<UserEntity>) {
        this.items = userEntities
        notifyDataSetChanged()
    }

    class ViewHolder(private val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(userEntity: UserEntity) {
            viewDataBinding.setVariable(BR.viewModel, userEntity)
            viewDataBinding.executePendingBindings()
        }
    }
}