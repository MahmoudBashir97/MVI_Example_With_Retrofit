package com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudbashir.Mvi_example_with_retrofit.R
import com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.Model

class PostsAdapter(val mlist:List<Model>): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var tv_title: TextView = itemView.findViewById(R.id.tv_title)
         var tv_desc: TextView = itemView.findViewById(R.id.tv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_item_post,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_title.text = mlist[position].title
        holder.tv_desc.text = mlist[position].body
    }

    override fun getItemCount(): Int = mlist.size
}