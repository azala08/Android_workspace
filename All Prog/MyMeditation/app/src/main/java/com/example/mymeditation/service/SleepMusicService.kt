package com.example.mymeditation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mymeditation.model.Sleep
import com.example.mymeditation.R
import com.example.mymeditation.adapter.MusicItemAdapter

class SleepMusicService : Service() {

    inner class SleepMusicBinder : Binder() {
        fun getService(): SleepMusicService = this@SleepMusicService
    }

    private val binder = SleepMusicBinder()
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var notificationManager: NotificationManagerCompat

    private var sleepList: ArrayList<Sleep> = arrayListOf()
    private var currentPosition: Int = 0
    private var isPlaying = false

    companion object {
        const val ACTION_START = "SLEEP_ACTION_START"
        const val ACTION_PLAY_PAUSE = "SLEEP_ACTION_PLAY_PAUSE"
        const val ACTION_NEXT = "SLEEP_ACTION_NEXT"
        const val ACTION_PREVIOUS = "SLEEP_ACTION_PREVIOUS"
        const val ACTION_UPDATE_UI = "SLEEP_ACTION_UPDATE_UI"
        const val CHANNEL_ID = "SLEEP_MUSIC_CHANNEL"
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onCreate() {
        super.onCreate()

        notificationManager = NotificationManagerCompat.from(this)

        createNotificationChannel()

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Sleep Music",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {

            ACTION_START -> {
                stopOtherService()

                sleepList = intent.getParcelableArrayListExtra("sleep_list") ?: arrayListOf()
                currentPosition =
                    intent.getIntExtra("current_position", 0).coerceIn(0, sleepList.size - 1)
                startMusic()
            }

            ACTION_PLAY_PAUSE -> {
                if (isPlaying) pauseMusic() else resumeMusic()
                sendBroadcast(Intent(ACTION_UPDATE_UI))
                showNotification()
            }

            ACTION_NEXT -> {
                playNext()
                sendBroadcast(Intent(ACTION_UPDATE_UI))
            }

            ACTION_PREVIOUS -> {
                playPrevious()
                sendBroadcast(Intent(ACTION_UPDATE_UI))
            }
        }

        return START_STICKY
    }

    private fun startMusic() {
        mediaPlayer?.release()
        val sleep = sleepList.getOrNull(currentPosition) ?: return
        mediaPlayer = MediaPlayer.create(this, sleep.audioResId)
        mediaPlayer?.setOnCompletionListener {
            playNext()
            sendBroadcast(Intent(ACTION_UPDATE_UI))
        }
        mediaPlayer?.start()
        isPlaying = true
        showNotification()
    }

    private fun stopOtherService() {
        val stopIntent = Intent(this, MusicService::class.java)
        stopService(stopIntent)
    }

    private fun prepareAndPlay() {
        releasePlayer()
        if (sleepList.isEmpty()) return

        val sleepItem = sleepList[currentPosition]
        mediaPlayer = MediaPlayer.create(this, sleepItem.audioResId).apply {
            setOnCompletionListener { next() }
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
        if (sleepList.isEmpty()) return
        currentPosition = (currentPosition + 1) % sleepList.size
        prepareAndPlay()
    }

    private fun previous() {
        if (sleepList.isEmpty()) return
        currentPosition = if (currentPosition - 1 < 0) sleepList.size - 1 else currentPosition - 1
        prepareAndPlay()
    }

    private fun showNotification() {
        val sleep = getCurrentSleep() ?: return

        val remoteView = RemoteViews(packageName, R.layout.custom_notification_design).apply {
            setTextViewText(R.id.notification_title_sleep, sleep.title)
            setImageViewResource(
                R.id.notification_play_pause_Sleep,
                if (isPlaying) R.drawable.sleep_pause else R.drawable.sleep_play
            )

            setOnClickPendingIntent(
                R.id.notification_play_pause_Sleep,
                getPendingIntent(ACTION_PLAY_PAUSE)
            )
            setOnClickPendingIntent(R.id.notification_next_sleep, getPendingIntent(ACTION_NEXT))
            setOnClickPendingIntent(R.id.notification_prev_sleep, getPendingIntent(ACTION_PREVIOUS))
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.music)
            .setContent(remoteView)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOnlyAlertOnce(true)
            .setOngoing(isPlaying)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .build()

        startForeground(1, notification)
    }

    private fun getPendingIntent(action: String): PendingIntent {
        val intent = Intent(this, SleepMusicService::class.java).apply { this.action = action }
        return PendingIntent.getService(
            this,
            action.hashCode(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun broadcastUpdate() {
        val intent = Intent(ACTION_UPDATE_UI).apply {
            putExtra("isPlaying", isPlaying)
            putExtra("title", sleepList.getOrNull(currentPosition)?.title ?: "")
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
        mediaPlayer?.release()
        mediaPlayer = null

        stopForeground(true)
        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopForeground(true)
        stopSelf()
    }

    // Public functions for binding
    fun getCurrentSleep(): Sleep? = sleepList.getOrNull(currentPosition)


    fun pauseMusic() {
        mediaPlayer?.pause()
        isPlaying = false
        showNotification()
    }

    fun resumeMusic() {
        mediaPlayer?.start()
        isPlaying = true
        showNotification()
    }

    fun playNext() {
        currentPosition = (currentPosition + 1) % sleepList.size
        startMusic()
        sendBroadcast(Intent(ACTION_UPDATE_UI))
    }

    fun playPrevious() {
        currentPosition = if (currentPosition - 1 < 0) sleepList.lastIndex else currentPosition - 1
        startMusic()
        sendBroadcast(Intent(ACTION_UPDATE_UI))
    }


    fun seekTo(ms: Int) {
        mediaPlayer?.seekTo(ms)
    }

    fun seekBy(ms: Int) {
        mediaPlayer?.let {
            val newPos = it.currentPosition + ms
            it.seekTo(newPos.coerceIn(0, it.duration))
        }
    }

    fun isPlaying(): Boolean = isPlaying

    fun getDuration(): Int = mediaPlayer?.duration ?: 0

    fun getCurrentPosition(): Int = mediaPlayer?.currentPosition ?: 0
}