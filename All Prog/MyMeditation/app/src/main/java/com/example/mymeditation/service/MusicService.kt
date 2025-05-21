
package com.example.mymeditation.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mymeditation.R
import com.example.mymeditation.model.MusicItem

class MusicService : Service() {

    inner class MusicServiceBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    private val binder = MusicServiceBinder()
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var notificationManager: NotificationManagerCompat

    private var musicList: ArrayList<MusicItem> = arrayListOf()
    private var currentPosition: Int = 0
    private var isPlaying = false

    fun getCurrentPositionIndex(): Int = currentPosition

    override fun onCreate() {
        super.onCreate()
        notificationManager = NotificationManagerCompat.from(this)
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_PLAY_PAUSE = "ACTION_PLAY_PAUSE"
        const val ACTION_NEXT = "ACTION_NEXT"
        const val ACTION_PREVIOUS = "ACTION_PREVIOUS"
        const val CHANNEL_ID = "MUSIC_CHANNEL"
        const val ACTION_UPDATE_UI = "ACTION_UPDATE_UI"
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> {
                stopOtherService()
                musicList = intent.getParcelableArrayListExtra("music_list") ?: arrayListOf()
                currentPosition = intent.getIntExtra("current_position", 0)
                prepareAndPlay()
            }
            ACTION_PLAY_PAUSE -> togglePlayPause()
            ACTION_NEXT -> {
                next()
                broadcastUpdate()
            }
            ACTION_PREVIOUS -> {
                previous()
                broadcastUpdate()
            }
        }
        return START_NOT_STICKY
    }

    private fun stopOtherService() {
        stopService(Intent(this, SleepMusicService::class.java))
    }

    private fun prepareAndPlay() {
        releasePlayer()
        if (musicList.isEmpty()) return

        val song = musicList[currentPosition]
        mediaPlayer = if (song.filePath.isNotBlank()) {
            MediaPlayer().apply {
                setDataSource(song.filePath)
                prepare()
            }
        } else {
            MediaPlayer.create(this, song.audioResId)
        }
            .apply {
            setOnCompletionListener {
                next()
                broadcastUpdate()
            }
            start()
        }
        isPlaying = true
        showNotification()
        broadcastUpdate()
    }

    private fun togglePlayPause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                isPlaying = false
            } else {
                it.start()
                isPlaying = true
            }
            showNotification()
            broadcastUpdate()
        }
    }

    private fun next() {
        if (musicList.isEmpty()) return
        currentPosition = (currentPosition + 1) % musicList.size
        prepareAndPlay()
    }

    private fun previous() {
        if (musicList.isEmpty()) return
        currentPosition = if (currentPosition - 1 < 0) musicList.size - 1 else currentPosition - 1
        prepareAndPlay()
    }

    private fun showNotification() {
        if (musicList.isEmpty()) return

        val song = musicList[currentPosition]
        val playPauseIcon = if (isPlaying) R.drawable.pause_dark_music else R.drawable.play_dark_music

        val playPauseIntent = PendingIntent.getService(
            this, 0,
            Intent(this, MusicService::class.java).setAction(ACTION_PLAY_PAUSE),
            PendingIntent.FLAG_IMMUTABLE
        )
        val nextIntent = PendingIntent.getService(
            this, 1,
            Intent(this, MusicService::class.java).setAction(ACTION_NEXT),
            PendingIntent.FLAG_IMMUTABLE
        )
        val prevIntent = PendingIntent.getService(
            this, 2,
            Intent(this, MusicService::class.java).setAction(ACTION_PREVIOUS),
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationLayout = RemoteViews(packageName, R.layout.custom_notification_music).apply {
            setTextViewText(R.id.notification_title, song.title)
            setImageViewResource(R.id.notification_play_pause, playPauseIcon)
            setOnClickPendingIntent(R.id.notification_play_pause, playPauseIntent)
            setOnClickPendingIntent(R.id.notification_next, nextIntent)
            setOnClickPendingIntent(R.id.notification_prev, prevIntent)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.music_notification)
            .setCustomContentView(notificationLayout)
            .setOnlyAlertOnce(true)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()

        startForeground(1, notification)
    }

    private fun broadcastUpdate() {
        val currentItem = musicList.getOrNull(currentPosition)
        val intent = Intent(ACTION_UPDATE_UI).apply {

            putExtra("current_position", currentPosition)
            putExtra("is_playing", isPlaying)
            putExtra("current_item_id", currentItem?.id)
            putExtra("title", currentItem?.title ?: "")
            putExtra("duration", mediaPlayer?.duration ?: 0)
            putExtra("position", mediaPlayer?.currentPosition ?: 0)
        }
        sendBroadcast(intent)
    }

    private fun releasePlayer() {
        mediaPlayer?.apply {
            stop()
            reset()
            release()
        }
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
        stopForeground(true)
    }
    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        releasePlayer()
        stopForeground(true)
        stopSelf()
    }

    fun getCurrentMusic(): MusicItem? = musicList.getOrNull(currentPosition)
    fun isPlaying(): Boolean = isPlaying

//    fun pauseMusic() {
//        mediaPlayer?.takeIf { it.isPlaying }?.pause()
//        isPlaying = false
//        showNotification()
//        broadcastUpdate()
//    }
//
//    fun resumeMusic() {
//        mediaPlayer?.takeIf { !it.isPlaying }?.start()
//        isPlaying = true
//        showNotification()
//        broadcastUpdate()
//    }
//
//    fun playNext() = next().also { broadcastUpdate() }
//    fun playPrevious() = previous().also { broadcastUpdate() }

    fun seekTo(ms: Int) {
        mediaPlayer?.seekTo(ms)
        broadcastUpdate()
    }

    fun seekBy(ms: Int) {
        mediaPlayer?.let {
            val newPos = it.currentPosition + ms
            it.seekTo(newPos.coerceIn(0, it.duration))
            broadcastUpdate()
        }
    }

    fun getDuration(): Int = mediaPlayer?.duration ?: 0
    fun getCurrentPosition(): Int = mediaPlayer?.currentPosition ?: 0
}

