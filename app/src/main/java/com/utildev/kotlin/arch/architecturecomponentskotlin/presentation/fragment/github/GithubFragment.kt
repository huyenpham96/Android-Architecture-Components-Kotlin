package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.github

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.Github
import com.utildev.kotlin.arch.architecturecomponentskotlin.databinding.FragmentGithubBinding
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_github.view.*
import org.greenrobot.eventbus.EventBus

class GithubFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentGithubBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_github, container, false)
        val viewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.fragGithub_btBack.setOnClickListener {
            EventBus.getDefault().post(Github("Architecture Components", "evenbus", "github.com"))
            clearStack()
        }
    }
}