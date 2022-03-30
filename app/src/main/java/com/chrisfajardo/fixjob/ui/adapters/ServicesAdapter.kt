package com.chrisfajardo.fixjob.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chrisfajardo.fixjob.R
import com.chrisfajardo.fixjob.data.reciyclerviewservices.Services

class ServicesAdapter(private val servicesList:List<Services>) : RecyclerView.Adapter<ServicesViewHolder>() {
    private var setArticleClickListener: ((article: Services) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServicesViewHolder(layoutInflater.inflate(R.layout.item_services,parent,false))

    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {

        val item = servicesList[position]
        holder.render(item)

        holder.itemView.setOnClickListener {

            setArticleClickListener?.let{

                it(item)
            }
        }

    }
    fun onArticleClicked(listener: (Services) -> Unit) {
        setArticleClickListener = listener
    }

    override fun getItemCount(): Int = servicesList.size





}