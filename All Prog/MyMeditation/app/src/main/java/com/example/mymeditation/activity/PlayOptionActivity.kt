package com.example.mymeditation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymeditation.R
import com.example.mymeditation.databinding.ActivityPlayOptionBinding
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.activity.SleepMusicPlayActivity
import com.example.mymeditation.adapter.SleepAdapter
import com.example.mymeditation.model.Sleep
import java.text.NumberFormat
import java.util.Locale

class PlayOptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayOptionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        SetStatusBar.fromActivity(this,true)

        val rootView = findViewById<View>(R.id.playoption)
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

        setupLikeButton()

        binding.imgPlayOpionDownload.setOnClickListener {
            Toast.makeText(applicationContext, "Downloded!!", Toast.LENGTH_SHORT).show()
        }

        val sleepList = listOf(
            Sleep(R.drawable.night_iceland, "Night Island", "45 MIN • SLEEP MUSIC", R.drawable.night_island_playoption, R.raw.hobbits),
            Sleep(R.drawable.sweet_sleep, "Sweet Sleep", "45 MIN • SLEEP MUSIC", R.drawable.sweet_sleep_playoption, R.raw.harrypotter),
            Sleep(R.drawable.good_night, "Good Night", "45 MIN • SLEEP MUSIC", R.drawable.good_night_playoption, R.raw.hobbits),
            Sleep(R.drawable.moon_cloud, "Moon Clouds", "45 MIN • SLEEP MUSIC", R.drawable.moon_clouds_playoption, R.raw.carefree)
        )

        val sleepItem = intent.getParcelableExtra<Sleep>("sleepItem")

        sleepItem?.let {
            binding.lyrRelativeSleepOption.setBackgroundResource(it.playOptionImageRes)
            binding.tvSleepOptionTitle.text = it.title

            binding.btnSleepPlay.setOnClickListener {
                val playIntent = Intent(this, SleepMusicPlayActivity::class.java)
                playIntent.putExtra("sleep_music_list", ArrayList(sleepList))
                playIntent.putExtra("sleep_current_position", sleepList.indexOf(sleepItem))
                startActivity(playIntent)
            }
        }

        binding.rvSleepOption.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        binding.rvSleepOption.adapter = SleepAdapter(sleepList)

        binding.imgSleepCourseBack.setOnClickListener {
            finish()
        }
    }

    private fun setupLikeButton() {
        var isLiked = false
        var likeCount = 24346

        binding.imgSleepLike.setOnClickListener {
            isLiked = !isLiked
            likeCount += if (isLiked) 1 else -1

            Toast.makeText(applicationContext, "Liked!", Toast.LENGTH_SHORT).show()

            binding.imgSleepLike.setImageResource(
                if (isLiked) R.drawable.heart_filled else R.drawable.select
            )

            val formattedCount = NumberFormat.getNumberInstance(Locale.US).format(likeCount)
            binding.tvSleepLikeCount.text = "$formattedCount Favorites"
        }
    }
    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed()
    {
        super.onBackPressed()
        finish()
    }
}

