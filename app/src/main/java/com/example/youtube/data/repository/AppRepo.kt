package com.example.youtube.data.repository

import com.example.youtube.data.api.ApiInterface
import javax.inject.Inject

class AppRepo @Inject constructor(
    private val api: ApiInterface
) {

    suspend fun getSearchList() = api.getSearchDataList("football")
}