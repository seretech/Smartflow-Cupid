package com.developers.serenity.cupid

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_adapter.view.*

class DataAdapter: RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private var dataList: List<AllDataClass>? = null

    fun updateData(dataList: List<AllDataClass>){
        this.dataList = dataList
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        @SuppressLint("SetTextI18n")
        fun bind(dataClass: AllDataClass) {
            itemView.name.text = dataClass.id?.toString()+"\n"+dataClass.category_name
            itemView.sl.text = "Slug :: "+dataClass.slug
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            view = LayoutInflater.from(parent.context).inflate(R.layout.data_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList?.get(position)!!)
    }
    override fun getItemCount(): Int {
        return if(dataList == null) 0
        else dataList?.size!!
    }
}