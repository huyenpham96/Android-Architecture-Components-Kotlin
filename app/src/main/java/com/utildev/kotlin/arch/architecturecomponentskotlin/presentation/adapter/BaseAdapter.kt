package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.R

open class BaseAdapter(
    private val layoutId: Int,
    recyclerView: RecyclerView,
    private val layoutManager: LinearLayoutManager?,
    private val adapterListener: BaseAdapter.AdapterListener?
) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {
    internal var items: MutableList<Any> = ArrayList()
    private var isLoading = true

    companion object {
        const val VIEW_TYPE_LOADING = 999
        const val VIEW_TYPE_NOTHING = 1000
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

    override fun getItemCount(): Int {
        return if (layoutManager == null) {
            items.size
        } else {
            if (items.size == 0) items.size else items.size + 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager != null) {
            when (//                layoutManager.itemCount <= 1 -> VIEW_TYPE_NOTHING
                position) {
                layoutManager.itemCount - 1 -> VIEW_TYPE_LOADING
                else -> position
            }
        } else {
            position
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
//            VIEW_TYPE_NOTHING -> BaseAdapter.ViewHolder(
//                DataBindingUtil.inflate(
//                    LayoutInflater.from(viewGroup.context), R.layout.view_blank, viewGroup, false
//                )
//            )
            VIEW_TYPE_LOADING -> BaseAdapter.ViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), R.layout.view_loadmore, viewGroup, false
                )
            )
            else -> BaseAdapter.ViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), layoutId, viewGroup, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    fun addAll(any: List<Any>) {
        this.items.addAll(any)
        notifyDataSetChanged()
    }

    fun add(any: Any) {
        this.items.add(any)
        notifyDataSetChanged()
    }

    fun set(any: List<Any>) {
        this.items.clear()
        addAll(any)
    }

    fun remove(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    fun setLoading(loading: Boolean) {
        this.isLoading = loading
    }

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    interface AdapterListener {
        fun onItemClick(`object`: Any)

        fun onItemLongClick(`object`: Any): Boolean

        fun onLoadMore()
    }
}