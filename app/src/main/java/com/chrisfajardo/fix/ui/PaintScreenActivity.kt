package com.chrisfajardo.fix.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisfajardo.fix.R
import com.chrisfajardo.fix.ui.adapters.PainScreenAdapter
import com.chrisfajardo.fix.ui.base.BaseActivity
import com.chrisfajardo.fix.ui.listeners.Listeners
import com.chrisfajardo.fix.viewModel.PaintScreenViewModel
import kotlinx.android.synthetic.main.activity_paintscreem.*

class PaintScreenActivity : BaseActivity(), Listeners {

    private lateinit var  adapter: PainScreenAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(PaintScreenViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paintscreem)

        val name= intent.getStringExtra("data")

        PaintScreenViewModel.addName(name!!)

        adapter= PainScreenAdapter(this,this)

        recyclerView.layoutManager = LinearLayoutManager (this)
        recyclerView.adapter = adapter
        observeData()

    }


    private fun observeData(){
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })

    }

    override fun callPhoneApp(phone: String) {
        baseCallPhoneApp(phone)
    }
    override fun whatsappButoom(phone: String){
        whatsappButoom1(phone)
    }


}
