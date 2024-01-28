package com.example.youtube.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes.APPLICATION_MP4
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class PlayerViewModel @Inject constructor(
    val player: ExoPlayer
):ViewModel() {

    var videoDuration = 0L
    fun setMediaItem(uri:Uri){

        val mediaItem = MediaItem.Builder().setUri(uri).setMimeType(APPLICATION_MP4).build()
        player.apply {
            addMediaItem(mediaItem)
            playWhenReady = true
        }
        player.apply { prepare() }
        videoDuration = player.duration

    }

    fun formatTime(): String {
        val seconds = videoDuration / 1000
        val minutes = seconds / 60
        return String.format("%02d:%02d", minutes.absoluteValue % 60, seconds.absoluteValue % 60)
    }


}