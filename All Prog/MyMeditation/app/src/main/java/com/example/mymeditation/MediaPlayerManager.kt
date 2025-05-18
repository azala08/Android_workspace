package com.example.mymeditation

import android.content.Context
import android.media.MediaPlayer

object MediaPlayerManager  {
    private var mediaPlayer: MediaPlayer? = null
    var currentPlayingResId: Int? = null

    fun playMusic(context: Context, resId: Int, onPlay: () -> Unit, onStop: () -> Unit) {
        stopMusic()
        mediaPlayer = MediaPlayer.create(context, resId)
        currentPlayingResId = resId
        mediaPlayer?.apply {
            setOnCompletionListener {
                stopMusic()
                onStop()
            }
            start()
            onPlay()
        }
    }

    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        currentPlayingResId = null
    }

    fun isPlaying(resId: Int): Boolean {
        return mediaPlayer?.isPlaying == true && currentPlayingResId == resId
    }

    fun isAnyMusicPlaying(): Boolean {
        return mediaPlayer?.isPlaying == true
    }

}