package com.dropbox.facts.screen.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dropbox.facts.R
import com.dropbox.facts.base.BaseActivity
import com.dropbox.facts.databinding.ActivityMainBinding
import com.dropbox.facts.screen.home.adapter.FactsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {

    private lateinit var adapter: FactsAdapter

    override val layoutResource: Int
        get() = R.layout.activity_main

    override val viewModelClassType: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()

        initLiveData()

        if (savedInstanceState == null) {
            viewModel.init()
        }
    }

    private fun initLiveData() {

        viewModel.errorToastLiveData.observe(this, Observer {
            Log.d(TAG, "errorToastLiveData received")
            showToast(it?.message)
        })

        viewModel.actionBarTitleLiveData.observe(this, Observer {
            Log.d(TAG, "actionBarTitleLiveData received: $it")
            it?.let { title ->
                toolbar?.title = title
            }
        })

        viewModel.factsLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setData(it)
                binding.swipeToRefreshContainer.isRefreshing = false
            }
        })
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        adapter = FactsAdapter()
        binding.recyclerView.adapter = adapter

        binding.swipeToRefreshContainer.setOnRefreshListener {
            viewModel.onSwipeRefreshRequested()
            binding.swipeToRefreshContainer.isRefreshing = true
        }
    }
}
