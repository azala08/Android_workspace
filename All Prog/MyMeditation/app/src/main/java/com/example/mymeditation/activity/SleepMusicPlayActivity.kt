package com.example.mymeditation.activity

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.*
import android.os.*
import android.support.v4.media.session.MediaControllerCompat
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mymeditation.R
import com.example.mymeditation.databinding.ActivitySleepMusicPlayBinding
import com.example.mymeditation.LikedSleepViewModel
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.model.Sleep
import com.example.mymeditation.service.SleepMusicService
import kotlinx.coroutines.launch

class SleepMusicPlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySleepMusicPlayBinding
    private var sleepList: ArrayList<Sleep> = arrayListOf()
    private var currentPosition: Int = 0

    private var sleepMusicService: SleepMusicService? = null
    private var isBound = false

    private lateinit var viewModel: LikedSleepViewModel

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as SleepMusicService.SleepMusicBinder
            sleepMusicService = binder.getService()
            isBound = true
            updateUI()
            startSeekBarUpdater()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            sleepMusicService = null
        }
    }

    private var handler: Handler? = null
    private var seekBarRunnable: Runnable? = null

    private val updateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == SleepMusicService.ACTION_UPDATE_UI) {

                updateUI()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepMusicPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SetStatusBar.fromActivity(this, true)
        applyWindowInsets(binding.mainSleepMusicPlay)

        sleepList = intent.getParcelableArrayListExtra("sleep_music_list") ?: arrayListOf()
        currentPosition = intent.getIntExtra("sleep_current_position", 0).coerceIn(0, sleepList.size - 1)

        val intent = Intent(this, SleepMusicService::class.java).apply {
            action = SleepMusicService.ACTION_START
            putParcelableArrayListExtra("sleep_list", sleepList)
            putExtra("current_position", currentPosition)
        }

        startService(intent)
        bindService(intent, connection, BIND_AUTO_CREATE)

        setupListeners()

        binding.imgSleepMusicLike.setOnClickListener {
            val current = sleepMusicService?.getCurrentSleep() ?: return@setOnClickListener
            lifecycleScope.launch {
                if (viewModel.isLiked(current.audioResId)) {
                    viewModel.unlike(current)
                    binding.imgSleepMusicLike.setImageResource(R.drawable.select)
                    Toast.makeText(this@SleepMusicPlayActivity, "Removed from favorites", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.like(current)
                    binding.imgSleepMusicLike.setImageResource(R.drawable.heart_filled)
                    Toast.makeText(this@SleepMusicPlayActivity, "Added to favorites", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel = ViewModelProvider(this)[LikedSleepViewModel::class.java]

    }

    private fun updateLikeIcon() {
        val current = sleepMusicService?.getCurrentSleep() ?: return
        lifecycleScope.launch {
            val liked = viewModel.isLiked(current.audioResId)
            binding.imgSleepMusicLike.setImageResource(
                if (liked) R.drawable.heart_filled else R.drawable.select
            )
        }
    }
    private fun startSeekBarUpdater() {
        handler = Handler(Looper.getMainLooper())
        seekBarRunnable = object : Runnable {
            override fun run() {
                sleepMusicService?.let {
                    binding.sbSleepMusicPlay.max = it.getDuration()
                    binding.sbSleepMusicPlay.progress = it.getCurrentPosition()
                    binding.tvSleepCurrentTime.text = formatTime(it.getCurrentPosition())
                    binding.tvSleepTotalTime.text = formatTime(it.getDuration())
                    binding.tvSleepMusicName.text = it.getCurrentSleep()?.title ?: ""
                }
                handler?.postDelayed(this, 500)
            }
        }
        handler?.post(seekBarRunnable!!)
    }

    private fun updateUI() {
        sleepMusicService?.let { service ->
            val isPlaying = service.isPlaying()
            val title = service.getCurrentSleep()?.title ?: ""
            val duration = service.getDuration()
            val position = service.getCurrentPosition()

            binding.tvSleepMusicName.text = title
            binding.sbSleepMusicPlay.max = duration
            binding.sbSleepMusicPlay.progress = position
            binding.tvSleepCurrentTime.text = formatTime(position)
            binding.tvSleepTotalTime.text = formatTime(duration)

            updateLikeIcon()

            binding.imgSleepMusicPlay.setImageResource(
                if (isPlaying) R.drawable.sleep_pause else R.drawable.sleep_play
            )
        }
    }

    private fun setupListeners() {
        binding.imgSleepMusicPlay.setOnClickListener {
            sleepMusicService?.let {
                if (it.isPlaying()) {
                    it.pauseMusic()
                    binding.imgSleepMusicPlay.setImageResource(R.drawable.sleep_play)
                } else {
                    it.resumeMusic()
                    binding.imgSleepMusicPlay.setImageResource(R.drawable.sleep_pause)
                }
            }
        }

        binding.imgMusicBackword.setOnClickListener {
            sleepMusicService?.seekBy(-15000)
        }

        binding.imgMusicForword.setOnClickListener {
            sleepMusicService?.seekBy(15000)
        }

        binding.imgMusicPreviousSleep.setOnClickListener {
            sleepMusicService?.playPrevious()
            updateUI()
            updateLikeIcon()
        }

        binding.imgMusicNextSleep.setOnClickListener {
            sleepMusicService?.playNext()
            updateUI()
            updateLikeIcon()
        }


        binding.imgSleepMusicCancel.setOnClickListener {
            cleanupAndFinish()
        }

        binding.sbSleepMusicPlay.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    sleepMusicService?.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

//        binding.imgSleepMusicLike.setOnClickListener {
//            Toast.makeText(applicationContext, "Liked", Toast.LENGTH_SHORT).show()
//        }
        binding.imgDownloadMusic.setOnClickListener {
            Toast.makeText(applicationContext, "Downloaded!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun applyWindowInsets(rootView: View) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBarsInsets.left,
                systemBarsInsets.top,
                systemBarsInsets.right,
                systemBarsInsets.bottom
            )
            insets
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(SleepMusicService.ACTION_UPDATE_UI)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(updateReceiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(updateReceiver, filter)
        }
    }


    override fun onPause() {
        super.onPause()
        unregisterReceiver(updateReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        cleanupAndFinish()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    private fun cleanupAndFinish() {
        handler?.removeCallbacks(seekBarRunnable!!)
        handler = null
        seekBarRunnable = null
        finish()
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(ms: Int): String {
        val totalSecs = ms / 1000
        val minutes = totalSecs / 60
        val seconds = totalSecs % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}
