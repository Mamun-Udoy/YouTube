package com.example.youtube.viewmodel

import android.app.Application
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.net.Uri
import android.util.TypedValue
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var videoDuration = 0L


    private val _isFullScreen = MutableLiveData<Boolean>()
    val isFullScreen: LiveData<Boolean> = _isFullScreen

    private var originalHeight = ViewGroup.LayoutParams.MATCH_PARENT
    private var originalWidth = 256.dpToPx()

    init {
        _isFullScreen.value = false
    }
    fun setMediaItem(uri:Uri){

        val mediaItem = MediaItem.Builder().setUri(uri).setMimeType(APPLICATION_MP4).build()
        player.apply {
            addMediaItem(mediaItem)
            playWhenReady = true
        }
        player.apply { prepare() }
        videoDuration = player.duration

    }

    fun toggleFullScreen(activity: FragmentActivity) {
        if (_isFullScreen.value == true) {
            // Revert to normal mode
            originalWidth to originalHeight
        } else {
            // Enter full-screen mode
            originalWidth = activity.window.decorView.width
            originalHeight = activity.window.decorView.height
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            0 to 0
        }
        _isFullScreen.value = !_isFullScreen.value!!
        // Reset orientation when exiting full-screen mode
        if (!_isFullScreen.value!!) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    fun formatTime(): String {
        val seconds = videoDuration / 1000
        val minutes = seconds / 60
        return String.format("%02d:%02d", minutes.absoluteValue % 60, seconds.absoluteValue % 60)
    }

    private fun Int.dpToPx(): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }


}