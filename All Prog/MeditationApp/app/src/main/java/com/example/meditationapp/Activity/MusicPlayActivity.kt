package com.example.meditationapp.Activity

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meditationapp.R

class MusicPlayActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playPauseBtn: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var tvCurrentTime: TextView
    private lateinit var tvTotalTime: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvSubtitle: TextView
    private var handler = Handler(Looper.getMainLooper())

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_play)

        playPauseBtn = findViewById(R.id.btnPlayPause)
        seekBar = findViewById(R.id.seekBar)
        tvCurrentTime = findViewById(R.id.tvCurrentTime)
        tvTotalTime = findViewById(R.id.tvTotalTime)
        tvTitle = findViewById(R.id.tvTitle)
        tvSubtitle = findViewById(R.id.tvSubtitle)

        val audioResId = intent.getIntExtra("AUDIO_RES_ID", R.raw.lion)
        val title = intent.getStringExtra("TITLE") ?: "Unknown"
        val subtitle = intent.getStringExtra("SUBTITLE") ?: ""

        tvTitle.text = title
        tvSubtitle.text = subtitle

        mediaPlayer = MediaPlayer.create(this, audioResId)
        seekBar.max = mediaPlayer.duration
        tvTotalTime.text = formatTime(mediaPlayer.duration)

        playPauseBtn.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                playPauseBtn.setImageResource(R.drawable.play)
            } else {
                mediaPlayer.start()
                playPauseBtn.setImageResource(R.drawable.pause)
            }
        }

        findViewById<ImageButton>(R.id.btnRewind).setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition - 15000)
        }

        findViewById<ImageButton>(R.id.btnForward).setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition + 15000)
        }

        handler.postDelayed(updateSeekBar, 1000)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer.seekTo(progress)
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })
    }

    private val updateSeekBar = object : Runnable {
        override fun run() {
            if (mediaPlayer.isPlaying) {
                seekBar.progress = mediaPlayer.currentPosition
                tvCurrentTime.text = formatTime(mediaPlayer.currentPosition)
                handler.postDelayed(this, 1000)
            }
        }
    }

    private fun formatTime(ms: Int): String {
        val minutes = ms / 1000 / 60
        val seconds = (ms / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onDestroy() {
        mediaPlayer.release()
        handler.removeCallbacks(updateSeekBar)
        super.onDestroy()
    }
}
