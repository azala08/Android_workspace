package com.example.meditationapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.meditationapp.Adapter.SongAdapter
import com.example.meditationapp.Model.Song
import com.example.meditationapp.R
import com.example.meditationapp.databinding.ActivityHappyMorningBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HappyMorningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHappyMorningBinding
//    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHappyMorningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
//        setupViewPager()
//        setupSongList()


        binding.favoriteCount.setOnClickListener {
            startActivity(Intent(applicationContext,MaditateActivity::class.java))
        }
    }

    private fun setupViews() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.favoriteCount.text = "24,234 Favorits"
        binding.listeningCount.text = "34,234 Listening"
    }

//    private fun setupViewPager() {
//        viewPager = binding.voicePager
//        tabLayout = binding.voiceTabLayout

//        val pagerAdapter = VoicePagerAdapter(this)
//        viewPager.adapter = pagerAdapter
//
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = if (position == 0) "MALE VOICE" else "FEMALE VOICE"
//        }.attach()
//    }

//    private fun setupSongList() {
//        binding.songList.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = SongAdapter(getSongList())
//        }
//    }

    private fun getSongList(): List<Song> = listOf(
        Song("Focus Attention", "10 MIN"),
        Song("Body Scan", "5 MIN"),
        Song("Making Happiness", "3 MIN")
    )
}
