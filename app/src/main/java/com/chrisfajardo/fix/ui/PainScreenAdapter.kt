package com.chrisfajardo.fix.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chrisfajardo.fix.R
import kotlinx.android.synthetic.main.item_row.view.*

class PainScreenAdapter(private val context: Context): RecyclerView.Adapter<PainScreenAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<UserPaintScreenActivity>()

    fun setListData(data:MutableList<UserPaintScreenActivity>){
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
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bindView(userActivity: UserPaintScreenActivity){
            Glide.with(context).load(userActivity.name).into(itemView.circleImageView)
            itemView.name.text=userActivity.name
            itemView.txt_tittle.text = userActivity.phone
            itemView.descripccion.text = userActivity.description

        }
    }

}