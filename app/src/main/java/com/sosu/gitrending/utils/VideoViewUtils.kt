package com.sosu.gitrending.utils

import android.R
import android.content.Context
import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

/**
 * Created by hyunsuso on 2020/07/06.
 */
object VideoViewUtils{

    fun onPlayGif(context: Context, videoView: VideoView, url : String){
        val uri: Uri = Uri.parse(url)

        videoView.setVideoURI(uri)
        videoView.start()
        videoView.setOnCompletionListener {
            it.reset()
            videoView.setVideoURI(uri)
            videoView.start()
        }
    }
}