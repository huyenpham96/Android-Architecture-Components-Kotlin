package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.remote

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange.RestItem
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.remote.stackexchange.RestUser
import com.utildev.kotlin.arch.architecturecomponentskotlin.databinding.FragmentRemoteBinding
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter.BaseAdapter
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter.UserRemoteAdapter
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_remote.view.*
import kotlinx.android.synthetic.main.view_list.view.*

class RemoteFragment : BaseFragment(), BaseAdapter.AdapterListener {
    private lateinit var viewModel: RemoteViewModel
    private var userRemoteAdapter: UserRemoteAdapter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var page = 1

    private var list: MutableList<RestItem> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentRemoteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_remote, container, false)
        viewModel = ViewModelProviders.of(this).get(RemoteViewModel::class.java)
        binding.viewModel = viewModel
        init(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.fragRemote_list.viewList_rvContent.run {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = userRemoteAdapter
        }
    }

    private fun init(view: View) {
        linearLayoutManager = GridLayoutManager(context, 1)
        userRemoteAdapter = UserRemoteAdapter(
            R.layout.item_user, view.fragRemote_list.viewList_rvContent, linearLayoutManager, this
        )

        viewModel.getUserStackExchange("desc", "reputation", "stackoverflow", page, true)
        viewModel.userListSE?.observe(this, Observer {
            if (it != null) {
                list.addAll(it.items!!)
                userRemoteAdapter!!.setLoading(true)
                userRemoteAdapter!!.addAll(list)
            }
        })
    }

    override fun onItemClick(`object`: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemLongClick(`object`: Any): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadMore() {
        viewModel.getUserStackExchange("desc", "reputation", "stackoverflow", ++page, false)
    }
}