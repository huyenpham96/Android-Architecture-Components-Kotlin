package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import com.utildev.kotlin.arch.architecturecomponentskotlin.R
import com.utildev.kotlin.arch.architecturecomponentskotlin.common.extensions.isNetworkAvailable
import com.utildev.kotlin.arch.architecturecomponentskotlin.data.room.model.Github
import com.utildev.kotlin.arch.architecturecomponentskotlin.databinding.ActivityMainBinding
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.BaseActivity
import com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.activity.second.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
//        mySharedPreferences.putString("aaa", "hello")
//        Toast.makeText(this, mySharedPreferences.getString("aaa"), Toast.LENGTH_SHORT).show()
        init()
    }

    private fun init() {
        viewModel.ob.observe(this, Observer {
            if (it == 1) {
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            }
        })

        actMain_bt.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(github: Github) {
        Toast.makeText(this, "${github.getTitle()} ${github.getLib()} ${github.getGithub()}", Toast.LENGTH_SHORT).show()
    }
}
