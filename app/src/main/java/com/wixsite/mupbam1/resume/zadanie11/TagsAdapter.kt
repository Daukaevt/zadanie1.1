package com.wixsite.mupbam1.resume.zadanie11

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TagsAdapter(val cntxt:Context, private val tags: ArrayList<String>, private val onClickListener: MyOnClickListener) : RecyclerView.Adapter<TagsAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tagTV: ImageView = itemView.findViewById<View>(R.id.tagTV) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tags_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tag = tags[position]

        Glide.with(cntxt).load(tag).centerCrop().into(holder.tagTV)

        holder.tagTV.setOnClickListener {
           onClickListener.onClicked(tag)
        }
    }

    override fun getItemCount(): Int {
        return tags.size
    }

}