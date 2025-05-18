package com.example.mymeditation.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.mymeditation.LikedMusicViewModel
import com.example.mymeditation.MusicViewModel
import com.example.mymeditation.R
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.adapter.MusicItemAdapter
import com.example.mymeditation.databinding.ActivityMusicPlayBinding
import com.example.mymeditation.model.DownloadMusicEntity
import com.example.mymeditation.model.MusicItem
import com.example.mymeditation.service.MusicService
import kotlinx.coroutines.launch
import java.io.IOException

class MusicPlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicPlayBinding
    private var musicService: MusicService? = null
    private var isBound = false
    private val handler = Handler(Looper.getMainLooper())

    private lateinit var viewModel: LikedMusicViewModel
    private lateinit var dbViewModel: MusicViewModel
    private lateinit var musicList: ArrayList<MusicItem>
    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    101
                )
            }
        }


        viewModel = ViewModelProvider(this)[LikedMusicViewModel::class.java]
        dbViewModel = ViewModelProvider(this)[MusicViewModel::class.java]

        SetStatusBar.fromActivity(this, false)
        applyWindowInsets(binding.mainMusicPlay)
        setupListeners()
        setupNotificationChannel()

        musicList = intent.getParcelableArrayListExtra("music_list") ?: arrayListOf()
        currentPosition = intent.getIntExtra("current_position", 0)

        val intent = Intent(this, MusicService::class.java).apply {
            action = MusicService.ACTION_START
            putParcelableArrayListExtra("music_list", musicList)
            putExtra("current_position", currentPosition)
        }
        startService(intent)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        LocalBroadcastManager.getInstance(this).registerReceiver(downloadUpdateReceiver,
            IntentFilter("DOWNLOAD_UPDATED"))

        updateDownloadIcon()
        downloadMusic()
    }


    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicServiceBinder
            musicService = binder.getService()
            isBound = true

            MusicItemAdapter.PlaybackState.currentPlayingItemId = musicService?.getCurrentMusic()?.id
            MusicItemAdapter.PlaybackState.isPlaying = musicService?.isPlaying() ?: false

            syncUI()
            startSeekBarUpdates()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
            isBound = false
        }
    }

    private fun downloadMusic() {
        binding.imgMusicDownload.setOnClickListener {
            val currentSong = musicList[currentPosition]
            lifecycleScope.launch {
                val isAlreadyDownloaded = dbViewModel.isDownloaded(currentSong.audioResId)
                if (isAlreadyDownloaded) {
                    Toast.makeText(this@MusicPlayActivity, "This song is already downloaded.", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                        requestFileCreation()
                    } else {
                        requestPermissions(arrayOf(Manifest.permission.READ_MEDIA_AUDIO), 1002)
                    }
                } else {
                    requestFileCreation()
                }
            }
        }
    }

    private fun requestFileCreation() {
        val currentSong = musicList[currentPosition]
        val fileName = "${currentSong.title}_${System.currentTimeMillis()}.mp3"
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "audio/mpeg"
            putExtra(Intent.EXTRA_TITLE, fileName)
        }
        startActivityForResult(intent, 1003)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                Toast.makeText(this, "Notification permission is required", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1003 && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                saveMusicFileToUri(uri)
            }
        }
    }

    private fun saveMusicFileToUri(uri: Uri) {
        val currentSong = musicList[currentPosition]
        val inputStream = resources.openRawResource(currentSong.audioResId)
        showDownloadNotification("Download Started", "Downloading ${currentSong.title}", true, 101)

        contentResolver.openOutputStream(uri)?.use { outputStream ->
            try {
                val buffer = ByteArray(4096)
                var length: Int
                while (inputStream.read(buffer).also { length = it } > 0) {
                    outputStream.write(buffer, 0, length)
                }
                outputStream.flush()

                lifecycleScope.launch {
                    dbViewModel.addDownloaded(
                        DownloadMusicEntity(
                            audioResId = currentSong.audioResId,
                            title = currentSong.title,
                            imageResId = currentSong.imageResId
                        )
                    )

                    // Broadcast update with the downloaded song ID
                    val updateIntent = Intent("DOWNLOAD_UPDATED")
                    updateIntent.putExtra("id", currentSong.audioResId)
                    LocalBroadcastManager.getInstance(this@MusicPlayActivity).sendBroadcast(updateIntent)

                    showDownloadNotification("Download Complete", "${currentSong.title} saved", false, 101)
                    Toast.makeText(this@MusicPlayActivity, "Download Complete", Toast.LENGTH_SHORT).show()
                }

            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Download Failed", Toast.LENGTH_SHORT).show()
            } finally {
                inputStream.close()
            }
        }
    }

    private fun updateDownloadIcon() {
        if (musicList.isEmpty() || currentPosition !in musicList.indices) return
        val currentSong = musicList[currentPosition]
        lifecycleScope.launch {
            val isDownloaded = dbViewModel.isDownloaded(currentSong.audioResId)
            binding.imgMusicDownload.setImageResource(
                if (isDownloaded) R.drawable.download_mark else R.drawable.download_music
            )
        }
    }

    private fun showDownloadNotification(title: String, content: String, isOngoing: Boolean, notificationId: Int) {
        val channelId = "music_download_channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Music Download Notifications", NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MusicPlayActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.music)
            .setContentTitle(title)
            .setContentText(content)
            .setContentIntent(pendingIntent)
            .setAutoCancel(!isOngoing)
            .setOngoing(isOngoing)
            .setOnlyAlertOnce(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        notificationManager.notify(notificationId, builder.build())
    }

    private fun setupListeners() {
        binding.imgMusicPlay.setOnClickListener { sendControlAction(MusicService.ACTION_PLAY_PAUSE) }
        binding.imgMusicNext.setOnClickListener { sendControlAction(MusicService.ACTION_NEXT) }
        binding.imgMusicPrevious.setOnClickListener { sendControlAction(MusicService.ACTION_PREVIOUS) }
        binding.imgMusicBackword.setOnClickListener { musicService?.seekBy(-15000) }
        binding.imgMusicForword.setOnClickListener { musicService?.seekBy(15000) }
        binding.imgMusicCancel.setOnClickListener { finish() }

        binding.sbMusicPlay.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) musicService?.seekTo(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun sendControlAction(action: String) {
        val intent = Intent(this, MusicService::class.java).apply { this.action = action }
        startService(intent)
    }

    private fun syncUI() {
        val current = musicService?.getCurrentMusic()
        currentPosition = musicService?.getCurrentPositionIndex() ?: currentPosition

        binding.tvMusicName.text = current?.title ?: ""
        binding.imgMusicPlay.setImageResource(if (musicService?.isPlaying() == true) R.drawable.pause_dark_music else R.drawable.play_dark_music)
        binding.sbMusicPlay.max = musicService?.getDuration() ?: 0
        binding.tvTotalTime.text = formatTime(musicService?.getDuration() ?: 0)
        binding.tvCurrentTime.text = formatTime(musicService?.getCurrentPosition() ?: 0)

        updateLikeIcon()
        updateDownloadIcon()
    }

    private fun startSeekBarUpdates() {
        handler.post(object : Runnable {
            override fun run() {
                if (isBound && musicService != null) {
                    val position = musicService!!.getCurrentPosition()
                    binding.sbMusicPlay.progress = position
                    binding.tvCurrentTime.text = formatTime(position)
                    handler.postDelayed(this, 500)
                }
            }
        })
    }

    private fun updateLikeIcon() {
        val current = musicService?.getCurrentMusic() ?: return
        lifecycleScope.launch {
            val liked = viewModel.isLiked(current.audioResId)
            binding.imgMusicLike.setImageResource(if (liked) R.drawable.heart_fill else R.drawable.like)
        }

        binding.imgMusicLike.setOnClickListener {
            lifecycleScope.launch {
                val liked = viewModel.isLiked(current.audioResId)
                if (liked) {
                    viewModel.unlike(current)
                    binding.imgMusicLike.setImageResource(R.drawable.like)
                } else {
                    viewModel.like(current)
                    binding.imgMusicLike.setImageResource(R.drawable.heart_fill)
                }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(ms: Int): String {
        val totalSecs = ms / 1000
        val min = totalSecs / 60
        val sec = totalSecs % 60
        return String.format("%02d:%02d", min, sec)
    }

    private fun applyWindowInsets(rootView: View) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom)
            insets
        }
    }

    private val downloadUpdateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val updatedId = intent?.getIntExtra("id", -1) ?: -1
            val currentSong = musicList.getOrNull(currentPosition)
            if (currentSong?.audioResId == updatedId) {
                updateDownloadIcon()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(downloadUpdateReceiver)
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(MusicService.ACTION_UPDATE_UI)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(playbackReceiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(playbackReceiver, filter)
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(playbackReceiver)
    }

    private val playbackReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == MusicService.ACTION_UPDATE_UI) {
                val playingId = intent.getStringExtra("current_item_id")
                val isPlaying = intent.getBooleanExtra("is_playing", false)

                MusicItemAdapter.PlaybackState.currentPlayingItemId = playingId
                MusicItemAdapter.PlaybackState.isPlaying = isPlaying

                syncUI() // This also calls updateDownloadIcon
            }
        }
    }

    private fun setupNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MusicService.CHANNEL_ID,
                "Music Playback",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Channel for music playback controls"
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}
