package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.BR
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange.RestItem

class UserSEAdapter: RecyclerView.Adapter<UserSEAdapter.ViewHolder>() {
    private var items: List<RestItem>? = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_user, viewGroup, false))

    override fun getItemCount() = items!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restItem = items!![position]
        holder.bind(restItem)
    }

    fun setItems(itemUsersList: List<RestItem>) {
        this.items = itemUsersList
        notifyDataSetChanged()
    }

    class ViewHolder(private val viewDataBinding: ViewDataBinding):RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(restItem: RestItem) {
            viewDataBinding.setVariable(BR.viewModel, restItem)
            viewDataBinding.executePendingBindings()
        }
    }
}