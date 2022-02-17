package com.chrisfajardo.fix.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisfajardo.fix.R
import com.chrisfajardo.fix.ui.adapters.LocksmithScreenAdapter
import com.chrisfajardo.fix.viewModel.LocksmithScreenViewModel
import kotlinx.android.synthetic.main.activity_locksmithscreen.*


class LocksmithScreenActivity : AppCompatActivity() {

    private lateinit var  adapter: LocksmithScreenAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(LocksmithScreenViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locksmithscreen)

        adapter= LocksmithScreenAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager (this)
        recyclerView.adapter = adapter
        observeData()

    }

    private fun observeData(){
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })}}