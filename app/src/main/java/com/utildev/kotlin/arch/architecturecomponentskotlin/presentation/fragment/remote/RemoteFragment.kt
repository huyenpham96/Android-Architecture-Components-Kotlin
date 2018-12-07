package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.remote

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.databinding.FragmentRemoteBinding
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter.UserSEAdapter
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_remote.view.*
import kotlinx.android.synthetic.main.view_list.view.*

class RemoteFragment : BaseFragment() {
    private lateinit var viewModel: RemoteViewModel
    private val userAdapter: UserSEAdapter by lazy { UserSEAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentRemoteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_remote, container, false)
        viewModel = ViewModelProviders.of(this).get(RemoteViewModel::class.java)
        binding.viewModel = viewModel
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.fragRemote_list.viewList_rvContent.run {
            layoutManager = GridLayoutManager(context, 1)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    private fun init() {
        viewModel.getUserStackExchange("desc", "reputation", "stackoverflow", 1, true)
        viewModel.userListSE?.observe(this, Observer {
            if (it != null) {
                userAdapter.setItems(it.items!!)
            }
        })
    }
}