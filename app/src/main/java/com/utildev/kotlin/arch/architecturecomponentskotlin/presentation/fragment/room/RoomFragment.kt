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
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter.UserRoomAdapter
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_room.view.*
import kotlinx.android.synthetic.main.view_list.view.*

class RoomFragment : BaseFragment() {
    private lateinit var viewModel: RoomViewModel
    private val roomAdapter: UserRoomAdapter by lazy { UserRoomAdapter() }
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
            layoutManager = GridLayoutManager(context, 1)
            setHasFixedSize(true)
            adapter = roomAdapter
        }
    }

    private fun init() {
        viewModel.getAllUser()
        viewModel.userLiveData?.observe(this, Observer {
            if (it != null) {
                roomAdapter.setItems(it)
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
}