package com.example.multimedia

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {
    lateinit var img1: ImageView
    lateinit var img2: ImageView
    lateinit var player: MediaPlayer
    lateinit var player2: MediaPlayer
    lateinit var img3: ImageView
    lateinit var img4: ImageView
    var audiourl = "https://prakrutitech.buzz/AndroidAPI/audio.mp3"
    lateinit var videoview: VideoView
    var videourl = "https://prakrutitech.buzz/AndroidAPI/movie.mp4"
    lateinit var surfaceView: SurfaceView
    lateinit var holder: SurfaceHolder
    lateinit var player3: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        player2 = MediaPlayer()
        player = MediaPlayer.create(applicationContext, R.raw.lion)
        videoview = findViewById(R.id.v1)
        surfaceView = findViewById(R.id.s1)

        holder = surfaceView.getHolder()
        holder.setFixedSize(800, 480)
        holder.addCallback(this)
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)



        try {
            val mediaController: MediaController = MediaController(this)
            mediaController.setAnchorView(videoview)
            val video = Uri.parse(videourl)
            videoview.setMediaController(mediaController)
            videoview.setVideoURI(video)
            videoview.start()
        } catch (e: Exception) {

        }



        img1.setOnClickListener {

            player.start()

        }
        img2.setOnClickListener {

            player.stop()

        }
        img3.setOnClickListener {

            player2.setDataSource(audiourl)
            player2.prepareAsync()
            player2.setOnPreparedListener(this)

        }
        img4.setOnClickListener {

            player2.stop()
        }

    }

    override fun onPrepared(mp: MediaPlayer?) {
        player2.start()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        player3.setDisplay(holder)
        play()
    }

    private fun play() {
        player3.setDataSource(videourl)
        player3.prepare()
        player3.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        TODO("Not yet implemented")
    }
}