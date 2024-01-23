package com.example.youtube.data.model

import com.google.gson.annotations.SerializedName

data class VideoResult(
    @SerializedName("userName")
    val userName: String,
    @SerializedName("videoUrl")
    val videoUrl: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("like")
    val like: String,
    @SerializedName("dislike")
    val dislike: String,
    @SerializedName("comment")
    val comment: String,
)