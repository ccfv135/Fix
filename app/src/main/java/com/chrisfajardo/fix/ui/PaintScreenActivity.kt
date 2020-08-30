package com.chrisfajardo.fix.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisfajardo.fix.R
import com.chrisfajardo.fix.viewModel.PaintScreenViewModel
import kotlinx.android.synthetic.main.activity_paintscreem.*

class PaintScreenActivity : AppCompatActivity() {

    private lateinit var  adapter: PainScreenAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(PaintScreenViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paintscreem)

        adapter= PainScreenAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager (this)
        recyclerView.adapter = adapter
        observeData()

    }

    fun observeData(){
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

}
