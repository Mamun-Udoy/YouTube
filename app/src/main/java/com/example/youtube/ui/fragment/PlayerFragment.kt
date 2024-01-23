package com.example.youtube.ui.fragment

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtube.R
import com.example.youtube.data.model.SearchResponse
import com.example.youtube.data.model.VideoResult
import com.example.youtube.databinding.FragmentPlayerBinding
import com.example.youtube.ui.adapter.PlayerAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding

    private val playerAdapter: PlayerAdapter by lazy { PlayerAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }


    private fun getData() {
        val gson = Gson()
        val data = arguments?.getString("data")
        val videoResult = gson.fromJson(data, SearchResponse.VideoResult::class.java)

        Log.d("videoResult", "getData: $videoResult")
        binding.txtTitle.text = videoResult.title
        binding.txtChannelName.text = videoResult.author



    }


}