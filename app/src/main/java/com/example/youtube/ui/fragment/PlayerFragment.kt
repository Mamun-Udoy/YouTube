package com.example.youtube.ui.fragment

import android.media.metrics.PlaybackStateEvent.STATE_ENDED
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_READY
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.data.model.SearchResponse
import com.example.youtube.databinding.FragmentPlayerBinding
import com.example.youtube.ui.adapter.PlayerAdapter
import com.example.youtube.utils.Dataset
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding

    private val playerAdapter: PlayerAdapter by lazy { PlayerAdapter() }

    private val mediaUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"
//    private val mediaUrlHls = "http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8"
    private var player: ExoPlayer?= null
    // Create a data source factory.
    private val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()

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
        initViews()
        preparePlayer()
    }

    override fun onPause() {
        super.onPause()
        pause()
    }

    override fun onResume() {
        super.onResume()
        play()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    @OptIn(UnstableApi::class) private fun preparePlayer(){
        player = context?.let {
            ExoPlayer.Builder(it)
                .build()
                .apply {

//                    val source = if (mediaUrl.contains("mp4"))
//                        getHlsMediaSource()
//                    else


                     val source=   getProgressiveMediaSource()
                    setMediaSource(source)
                    prepare()
                    play()
                    addListener(playerListener)
                    Log.d("peparedPlayer", "preparePlayer: preparePlayer")
                }
        }
    }



    private val playerListener = object: Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            when(playbackState){
                STATE_ENDED -> restartPlayer()
                STATE_READY -> {
                    binding.videoPlayer.player = player
                    play()
                    Log.d("playerVideo", "onPlaybackStateChanged: player running")
                }
            }
        }
    }

    private fun pause(){
        player?.playWhenReady = false
    }

    private fun play(){
        Log.d("readyForPlay", "play: ready to play the content")
        player?.playWhenReady = true
    }

    private fun restartPlayer(){
        player?.seekTo(0)
        player?.playWhenReady = true
    }

    private fun releasePlayer(){
        player?.apply {
            playWhenReady = false
            release()
        }
        player = null
    }


//    @OptIn(UnstableApi::class) private fun getHlsMediaSource(): MediaSource {
//        // Create a HLS media source pointing to a playlist uri.
//        return HlsMediaSource.Factory(dataSourceFactory).
////        createMediaSource(MediaItem.fromUri(mediaUrl))
//        createMediaSource(MediaItem.fromUri(mediaUrlHls))
//    }

    @OptIn(UnstableApi::class) private fun getProgressiveMediaSource(): MediaSource{
        // Create a Regular media source pointing to a playlist uri.
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(mediaUrl)))
    }



    private fun initViews() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playerAdapter
        }
        playerAdapter.submitList(Dataset.videoForPlayerPage)

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