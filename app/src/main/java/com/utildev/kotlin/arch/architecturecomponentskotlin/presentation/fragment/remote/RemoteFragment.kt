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
import com.utildev.kotlin.arch.architecturecomponentskotlin.databinding.FragmentRemoteBinding
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter.UserSEAdapter
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_remote.view.*
import kotlinx.android.synthetic.main.view_list.view.*

class RemoteFragment : BaseFragment(), UserSEAdapter.AdapterListener {
    private lateinit var viewModel: RemoteViewModel
    private lateinit var userAdapter: UserSEAdapter
    private val linearLayoutManager = GridLayoutManager(context, 1)
    private var page = 1

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
            adapter = userAdapter
        }
    }

    private fun init(view: View) {
        userAdapter = UserSEAdapter(view.fragRemote_list.viewList_rvContent, linearLayoutManager, this)

        viewModel.getUserStackExchange("desc", "reputation", "stackoverflow", page, true)
        viewModel.userListSE?.observe(this, Observer {
            if (it != null) {
                userAdapter.setLoading(true)
                userAdapter.setItems(it.items!!)
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