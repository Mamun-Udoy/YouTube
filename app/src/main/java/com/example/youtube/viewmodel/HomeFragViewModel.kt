package com.example.youtube.viewmodel

import androidx.lifecycle.ViewModel
import com.example.youtube.data.model.SearchResponse
import com.example.youtube.data.repository.AppRepo
import com.example.youtube.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeFragViewModel @Inject constructor(
    private val appRepo: AppRepo
) : ViewModel() {


    fun getFlowData(): Flow<Resource<SearchResponse>> = flow {
        val headers = mapOf(
            "Authorization" to "Bearer YOUR_ACCESS_TOKEN",
            "Custom-Header" to "Custom-Value"
        )
        try {
            emit(Resource.Loading)
            val posts = appRepo.getSearchList()
            emit(Resource.Success(posts))
        } catch (exp: Exception) {
            emit(Resource.Error(exp.message))
        }
    }.flowOn(Dispatchers.IO)
}