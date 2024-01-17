package com.example.youtube.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.data.model.SearchResponse
import com.example.youtube.databinding.FragmentHomeBinding
import com.example.youtube.ui.adapter.SearchListAdapter
import com.example.youtube.utils.Resource
import com.example.youtube.viewmodel.HomeFragViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(), SearchListAdapter.ItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragViewModel by viewModels()
    private val homeVideoPlayerAdapter: SearchListAdapter by lazy { SearchListAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        lifecycleScope.launch {
            fetchFlowData()
            Log.d("fetchData", "onViewCreated: ${fetchFlowData()}")
        }
    }

    private fun initViews() {
        binding.recyclerView.apply {
//            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = homeVideoPlayerAdapter
        }
    }

    private suspend fun fetchFlowData() {
        viewModel.getFlowData().collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressbar.visibility = View.GONE
                    homeVideoPlayerAdapter.responseResult = resource.data.videoResults!!
                }

                is Resource.Error -> {
                    binding.progressbar.visibility = View.GONE
                }
            }
        }
    }

    override fun onVideoPlayerClicked(item: SearchResponse.VideoResult, position: Int) {
        TODO("Not yet implemented")
    }



}