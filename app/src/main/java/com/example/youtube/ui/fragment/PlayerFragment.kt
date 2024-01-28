package com.example.youtube.ui.fragment

import android.media.metrics.PlaybackStateEvent.STATE_ENDED
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.OptIn
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
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
import com.example.youtube.R
import com.example.youtube.data.model.SearchResponse
import com.example.youtube.databinding.FragmentPlayerBinding
import com.example.youtube.ui.DoubleClickListener
import com.example.youtube.ui.adapter.PlayerAdapter
import com.example.youtube.utils.Dataset
import com.example.youtube.utils.onClick
import com.example.youtube.viewmodel.PlayerViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding

    private val playerAdapter: PlayerAdapter by lazy { PlayerAdapter() }

    private val viewModel: PlayerViewModel by viewModels()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

//    private val mediaUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"
////    private val mediaUrlHls = "http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8"
//    private var player: ExoPlayer?= null
//    // Create a data source factory.
//    private val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()


//    override fun onPause() {
//        super.onPause()
//        pause()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        play()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        releasePlayer()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(inflater)
        getData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        lifecycleScope.launch {
            preparePlayer()
        }
    }

    private fun preparePlayer() {

        viewModel.setMediaItem(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"))
        binding.videoPlayer.player = viewModel.player
        binding.videoPlayer.useController = true

        val seekBar: SeekBar = binding.videoPlayer.findViewById(R.id.seekBar)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            // this block run when we will shift the seekbar from one position to any position and give the update position
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val duration = viewModel.player.duration
                    val newPosition = (progress * duration) / 100
                    viewModel.player.seekTo(newPosition)
                    updateSeekBar()

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }


        })

        seekBar.max = 100
        coroutineScope.launch {
            while (isActive) {
                updateSeekBar()
                delay(1000)
            }
        }


        val play = binding.videoPlayer.findViewById<ImageView>(R.id.play)
        val forwardTimeLap = binding.videoPlayer.findViewById<ImageView>(R.id.forward_move)
        val backwardTimeLap = binding.videoPlayer.findViewById<ImageView>(R.id.back_move)
        val fullScreenSize = binding.videoPlayer.findViewById<ImageView>(R.id.fullScreenResize)
        val more = binding.videoPlayer.findViewById<ImageView>(R.id.more)
        val timeDuration = binding.videoPlayer.findViewById<TextView>(R.id.txtDuration)
        timeDuration.text = buildString {
            append(" / ")
            append(viewModel.formatTime())
        }

        play onClick {

            if (viewModel.player.isPlaying) {
                viewModel.player.pause()
                play.setImageResource(R.drawable.ic_pause)
            } else {
                viewModel.player.play()
                play.setImageResource(R.drawable.ic_play)
            }

        }

        val forwardDoubleClickListener = DoubleClickListener {
            // Code to execute on double click
            // This block will be executed when a double click is detected
            viewModel.player.seekTo(viewModel.player.currentPosition + 30000)
        }
        val backwardDoubleClickListener = DoubleClickListener {
            // Code to execute on double click
            // This block will be executed when a double click is detected
            viewModel.player.seekTo(viewModel.player.currentPosition - 30000)
        }


        forwardTimeLap onClick {

            viewModel.player.seekTo(viewModel.player.currentPosition + 5000)
        }
//        forwardTimeLap.setOnClickListener(forwardDoubleClickListener)

        backwardTimeLap onClick {
            viewModel.player.seekTo(viewModel.player.currentPosition - 5000)
        }
//        backwardTimeLap.setOnClickListener(backwardDoubleClickListener)


    }

    fun updateSeekBar() {
        val seekBar: SeekBar = binding.videoPlayer.findViewById(R.id.seekBar)
        coroutineScope.launch {
            while (isActive) {
                val duration = viewModel.player.duration
                val currentPosition = viewModel.player.currentPosition
                val progress =
                    if (duration > 0) (currentPosition * 100 / duration).toInt() else 0
                seekBar.progress = progress
//                val txtCurrentTime = binding.videoPlayer.findViewById<TextView>(R.id.txtCurrentTime)
//                txtCurrentTime.text = formatTime(currentPosition)
                delay(1000) // Update every second
            }
        }
    }


//    @OptIn(UnstableApi::class) private fun preparePlayer(){
//        player = context?.let {
//            ExoPlayer.Builder(it)
//                .build()
//                .apply {
//
////                    val source = if (mediaUrl.contains("mp4"))
////                        getHlsMediaSource()
////                    else
//
//
//                     val source=   getProgressiveMediaSource()
//                    setMediaSource(source)
//                    prepare()
//                    play()
//                    addListener(playerListener)
//                    Log.d("peparedPlayer", "preparePlayer: preparePlayer")
//                }
//        }
//    }
//
//
//
//    private val playerListener = object: Player.Listener {
//        override fun onPlaybackStateChanged(playbackState: Int) {
//            super.onPlaybackStateChanged(playbackState)
//            when(playbackState){
//                STATE_ENDED -> restartPlayer()
//                STATE_READY -> {
//                    binding.videoPlayer.player = player
//                    play()
//                    Log.d("playerVideo", "onPlaybackStateChanged: player running")
//                }
//            }
//        }
//    }
//
//    private fun pause(){
//        player?.playWhenReady = false
//    }
//
//    private fun play(){
//        Log.d("readyForPlay", "play: ready to play the content")
//        player?.playWhenReady = true
//    }
//
//    private fun restartPlayer(){
//        player?.seekTo(0)
//        player?.playWhenReady = true
//    }
//
//    private fun releasePlayer(){
//        player?.apply {
//            playWhenReady = false
//            release()
//        }
//        player = null
//    }
//
//
////    @OptIn(UnstableApi::class) private fun getHlsMediaSource(): MediaSource {
////        // Create a HLS media source pointing to a playlist uri.
////        return HlsMediaSource.Factory(dataSourceFactory).
//////        createMediaSource(MediaItem.fromUri(mediaUrl))
////        createMediaSource(MediaItem.fromUri(mediaUrlHls))
////    }
//
//    @OptIn(UnstableApi::class) private fun getProgressiveMediaSource(): MediaSource{
//        // Create a Regular media source pointing to a playlist uri.
//        return ProgressiveMediaSource.Factory(dataSourceFactory)
//            .createMediaSource(MediaItem.fromUri(Uri.parse(mediaUrl)))
//    }


    private fun initViews() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playerAdapter
        }
        playerAdapter.submitList(Dataset.videoForPlayerPage)

        viewModel.currentPosition.observe(viewLifecycleOwner) { currentPosition ->
            val txtCurrentTime = binding.videoPlayer.findViewById<TextView>(R.id.txtCurrentTime)
            txtCurrentTime.text = formatTime(currentPosition)
        }

    }

    private fun getData() {
        val gson = Gson()
        val data = arguments?.getString("data")
        val videoResult = gson.fromJson(data, SearchResponse.VideoResult::class.java)
        Log.d("videoResult", "getData: $videoResult")
        binding.txtTitle.text = videoResult.title
        binding.txtChannelName.text = videoResult.author

    }

    private fun formatTime(currentPosition: Long): String {
        Log.d("currentTime", "formatTime: check current time $currentPosition")
        val seconds =currentPosition / 1000
        val minutes = seconds / 60
        return String.format("%02d:%02d", minutes.absoluteValue % 60, seconds.absoluteValue % 60)
    }



}
