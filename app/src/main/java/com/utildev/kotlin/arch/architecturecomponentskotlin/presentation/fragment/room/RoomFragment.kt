package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.room

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.UserEntity
import com.utildev.kotlin.arch.architecturecomponentskotlin.databinding.FragmentRoomBinding
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter.BaseAdapter
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter.UserRoomAdapter
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_room.view.*
import kotlinx.android.synthetic.main.view_list.view.*

class RoomFragment : BaseFragment(), BaseAdapter.AdapterListener {
    private lateinit var viewModel: RoomViewModel
    private var roomAdapter: UserRoomAdapter? = null
    private val linearLayoutManager = GridLayoutManager(context, 1)
    private lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentRoomBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room, container, false)
        viewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)
        binding.viewModel = viewModel
        mView = binding.root
        init()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.fragRoom_list.viewList_rvContent.run {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = roomAdapter
        }
    }

    private fun init() {
        roomAdapter = UserRoomAdapter(R.layout.item_room, mView.fragRoom_list.viewList_rvContent, null, this)

        viewModel.getAllUser()
        viewModel.userLiveData?.observe(this, Observer {
            if (it != null) {
                roomAdapter!!.add(it)
            }
        })

        viewModel.getUserCount()
        viewModel.userCount!!.observe(this, Observer {
            mView.fragRoom_tvCount.text = it.toString()
        })

        mView.fragRoom_fab.setOnClickListener {
            viewModel.insertUser(UserEntity(0, "Kotlin", "Android Development"))
        }
    }

    override fun onItemClick(`object`: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemLongClick(`object`: Any): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}