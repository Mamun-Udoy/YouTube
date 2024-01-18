package com.example.youtube.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.data.model.SearchResponse
import com.example.youtube.databinding.SingleSearchItemBinding

class SearchListAdapter(private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<SearchListAdapter.MyViewHolder>() {

    var responseResult: List<SearchResponse.VideoResult?> = emptyList()

    inner class MyViewHolder(val binding: SingleSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            SingleSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = responseResult.size
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = responseResult[position]
        holder.binding.data = item
        Log.d("api_result", "viewHolder: ${responseResult[position]}")
        Log.d("api_result", "viewHolder: ${responseResult[position]}")


        Glide.with(holder.binding.imgThumbnail.context)
            .load(item?.thumbnail)
            .into(holder.binding.imgThumbnail)

        holder.binding.imgThumbnail.setOnClickListener {
            if (item != null) {
                clickListener.onVideoPlayerClicked(item)
            }

        }

    }

    interface ItemClickListener {

        fun onVideoPlayerClicked(item: SearchResponse.VideoResult)

    }
}