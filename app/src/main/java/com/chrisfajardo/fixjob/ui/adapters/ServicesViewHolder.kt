package com.chrisfajardo.fixjob.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chrisfajardo.fixjob.R
import com.chrisfajardo.fixjob.data.reciyclerviewservices.Services

class ServicesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val services = view.findViewById<TextView>(R.id.TittleService)
    val Image = view.findViewById<ImageView>(R.id.imageServices)

    fun render(servicesModel: Services){
        services.text = servicesModel.serviceName
        Glide.with(Image.context).load(servicesModel.serviceImage).into(Image)

    }
}