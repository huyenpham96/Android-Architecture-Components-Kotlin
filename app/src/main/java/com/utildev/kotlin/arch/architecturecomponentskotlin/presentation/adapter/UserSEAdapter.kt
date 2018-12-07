package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.BR
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange.RestItem

class UserSEAdapter(
    recyclerView: RecyclerView,
    private val layoutManager: LinearLayoutManager?,
    private val adapterListener: AdapterListener?
) : RecyclerView.Adapter<UserSEAdapter.ViewHolder>() {
    private var items: MutableList<RestItem> = ArrayList()
    private var isLoading = true

    companion object {
        const val VIEW_TYPE_ITEM = 555
        const val VIEW_TYPE_LOADING = 111
    }

    init {
        if (layoutManager != null && adapterListener != null) {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var visibleItemCount: Int = 0
                var totalItemCount: Int = 0
                var firstVisibleItem: Int = 0
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        visibleItemCount = layoutManager.childCount
                        totalItemCount = layoutManager.itemCount
                        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                        if (isLoading) {
                            if (visibleItemCount + firstVisibleItem >= totalItemCount) {
                                adapterListener.onLoadMore()
                                isLoading = false
                            }
                        }
                    }
                }
            })
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == VIEW_TYPE_LOADING) {
            ViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context),
                    R.layout.view_loadmore,
                    viewGroup,
                    false
                )
            )
        } else {
            ViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_user, viewGroup, false)
            )
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager != null) {
            if (position + 1 == layoutManager.itemCount) {
                VIEW_TYPE_LOADING
            } else VIEW_TYPE_ITEM
        } else {
            super.getItemViewType(position)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restItem = items[position]
        holder.bind(restItem)
    }

    fun setItems(itemUsersList: List<RestItem>) {
        this.items.addAll(itemUsersList)
        notifyDataSetChanged()
    }

    fun setLoading(loading: Boolean) {
        this.isLoading = loading
    }

    class ViewHolder(private val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(restItem: RestItem) {
            viewDataBinding.setVariable(BR.viewModel, restItem)
            viewDataBinding.executePendingBindings()
        }
    }

    interface AdapterListener {
        fun onItemClick(`object`: Any)

        fun onItemLongClick(`object`: Any): Boolean

        fun onLoadMore()
    }
}