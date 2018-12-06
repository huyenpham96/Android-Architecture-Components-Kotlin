package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.room

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.databinding.FragmentRoomBinding
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment

class RoomFragment : BaseFragment() {
    private lateinit var binding: FragmentRoomBinding
    private lateinit var viewModel: RoomViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room, container, false)
        viewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }
}