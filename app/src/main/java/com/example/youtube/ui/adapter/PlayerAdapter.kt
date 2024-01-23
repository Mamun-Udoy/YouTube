package com.example.youtube.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.R
import com.example.youtube.data.model.SearchResponse


import com.example.youtube.databinding.SingleSearchItemBinding

class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.MyViewHolder>() {

    private var playerVideoItem: MutableList<SearchResponse.VideoResult?> = mutableListOf()

    inner class MyViewHolder(val binding: SingleSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.MyViewHolder {
        val binding =
            SingleSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerAdapter.MyViewHolder, position: Int) {
        val item = playerVideoItem[position]
        holder.binding.data = item

//        holder.binding.imgThumbnail.setImageResource()

        Glide.with(holder.binding.imgThumbnail.context)
            .load(item?.thumbnail)
            .error(R.drawable.profile)
            .into(holder.binding.imgThumbnail)
    }

    override fun getItemCount(): Int = playerVideoItem.size

    fun submitList(list: List<SearchResponse.VideoResult>){
        playerVideoItem.clear()
        playerVideoItem.addAll(list)
        notifyDataSetChanged()
    }
}