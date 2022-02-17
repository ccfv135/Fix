package com.chrisfajardo.fix.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chrisfajardo.fix.R
import com.chrisfajardo.fix.ui.listeners.Listeners
import com.chrisfajardo.fix.ui.users.UserBuildScreenActivity
import kotlinx.android.synthetic.main.item_row.view.*

class BuildingScreenAdapter (private val context: Context,private val listener:Listeners): RecyclerView.Adapter<BuildingScreenAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<UserBuildScreenActivity>()

    fun setListData(data: MutableList<UserBuildScreenActivity>){
        dataList =data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0){
            dataList.size
        }else{0
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = dataList[position]
        holder.bindView(user)
        holder.itemView.llamaruno.setOnClickListener {
            val phone = user.phone
            listener.callPhoneApp(phone)
        }
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(userActivity: UserBuildScreenActivity){
            Glide.with(context).load(userActivity.url).into(itemView.circleImageView)
            itemView.name.text=userActivity.name
            itemView.txt_tittle.text = userActivity.phone
            itemView.descripccion.text = userActivity.description



        }
    }}
