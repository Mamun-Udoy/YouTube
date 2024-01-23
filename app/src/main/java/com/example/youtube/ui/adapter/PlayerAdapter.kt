package com.example.youtube.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.data.model.VideoResult
import com.example.youtube.databinding.FragmentPlayerBinding

class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.MyViewHolder>() {

    var playerVideoItem: ArrayList<VideoResult> = arrayListOf()

    inner class MyViewHolder(val binding: FragmentPlayerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.MyViewHolder {
        val binding =
            FragmentPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerAdapter.MyViewHolder, position: Int) {
        val item = playerVideoItem[position]
        holder.binding.data = item
    }

    override fun getItemCount(): Int = playerVideoItem.size
}