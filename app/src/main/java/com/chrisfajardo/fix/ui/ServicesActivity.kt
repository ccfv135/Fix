package com.chrisfajardo.fix.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrisfajardo.fix.R
import com.chrisfajardo.fix.data.reciyclerviewservices.ServicesProvider
import com.chrisfajardo.fix.ui.adapters.ServicesAdapter

class ServicesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)
        initRecyclerView()


    }

    fun initRecyclerView() {
        val recyclerview = findViewById<RecyclerView>(R.id.servicesRecyclerview)
        recyclerview.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val servicesAdapter = ServicesAdapter(ServicesProvider.serviceList)
        recyclerview.adapter = servicesAdapter
        recyclerview.layoutManager = GridLayoutManager(applicationContext, 2)

        servicesAdapter.onArticleClicked {

            val name = it.serviceName
            val intent = Intent(this, PaintScreenActivity::class.java)
            intent.putExtra("data",name)
            startActivity(intent)


        }


    }
}
