package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.remote

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.databinding.FragmentRemoteBinding
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment

class RemoteFragment : BaseFragment() {
    private lateinit var binding: FragmentRemoteBinding
    private lateinit var viewModel: RemoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_remote, container, false)
        viewModel = ViewModelProviders.of(this).get(RemoteViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }
}