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
import com.utildev.kotlin.arch.architecturecomponentskotlin.databinding.FragmentRemoteBinding
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter.BaseAdapter
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter.UserRemoteAdapter
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_remote.view.*
import kotlinx.android.synthetic.main.view_list.view.*

class RemoteFragment : BaseFragment(), BaseAdapter.AdapterListener {
  private lateinit var viewModel: RemoteViewModel
  private lateinit var rootView: View
  private var userRemoteAdapter: UserRemoteAdapter? = null
  private lateinit var linearLayoutManager: LinearLayoutManager

  private var list: MutableList<RestItem> = ArrayList()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val binding: FragmentRemoteBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_remote, container, false)
    viewModel = ViewModelProviders.of(this).get(RemoteViewModel::class.java)
    binding.viewModel = viewModel
    rootView = binding.root
    init()
    return rootView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.fragRemote_list.viewList_rvContent.run {
      layoutManager = linearLayoutManager
      setHasFixedSize(true)
      adapter = userRemoteAdapter
    }
  }

  private fun init() {
    linearLayoutManager = GridLayoutManager(context, 1)
    if (userRemoteAdapter == null) {
      userRemoteAdapter = UserRemoteAdapter(
        R.layout.item_user, rootView.fragRemote_list.viewList_rvContent, linearLayoutManager, this
      )
      if (viewModel.userListSE == null) {
        viewModel.getUserStackExchange("desc", "reputation", "stackoverflow", viewModel.page, true)
      } else {
        list.addAll(viewModel.storeList)
        userRemoteAdapter!!.isLoading = true
        userRemoteAdapter!!.set(list)
      }
    }

    rootView.fragRemote_list.viewList_srLayout.setOnRefreshListener {
      viewModel.page = 1
      viewModel.storeList.clear()
      list.clear()
      userRemoteAdapter!!.set(list)
      userRemoteAdapter!!.isEndList = false
      viewModel.getUserStackExchange("desc", "reputation", "stackoverflow", viewModel.page, true)
      rootView.fragRemote_list.viewList_srLayout.isRefreshing = false
    }

    viewModel.userListSE?.observe(this, Observer {
      if (it != null) {
        list.clear()
        list.addAll(it)
        userRemoteAdapter!!.isLoading = true
        userRemoteAdapter!!.set(list)
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
    if (list.size < 100) {
      viewModel.getUserStackExchange("desc", "reputation", "stackoverflow", ++viewModel.page, false)
    } else {
      userRemoteAdapter!!.isEndList = true
    }
  }
}