package com.example.youtube.data.api

import com.example.youtube.utils.API_KEY
import com.example.youtube.utils.DEVICE
import com.example.youtube.utils.ENGINE
import com.example.youtube.utils.GL
import com.example.youtube.utils.HL
import com.example.youtube.utils.SEARCH
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(SEARCH)
    suspend fun getSearchDataList(
//        @HeaderMap headers: Map<String, String> = emptyMap(),
        @Query("q") query: String,
        @Query("engine") engine: String = ENGINE,
        @Query("device") device: String = DEVICE,
        @Query("hl") hl: String = HL,
        @Query("gl") gl: String = GL,
        @Query("api_key") apiKey: String = API_KEY
    ): SearchResponse

}