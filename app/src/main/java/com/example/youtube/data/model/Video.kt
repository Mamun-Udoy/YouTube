package com.example.youtube.data.model

import com.google.gson.annotations.SerializedName

data class VideoResult(
    @SerializedName("author")
    val author: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("position")
    val position: Int?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("title")
    val title: String?
)