package com.example.meditationsleep

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlayOptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_option)

        val musicItem = intent.getParcelableExtra<SleepItem>("musicItem")

        musicItem?.let { item ->
            findViewById<ImageView>(R.id.ivMusicImage).setImageResource(item.imageResId)
            findViewById<TextView>(R.id.tvTitle).text = item.title
            findViewById<TextView>(R.id.tvDuration).text = item.duration
            findViewById<TextView>(R.id.tvCategory).text = item.category
            findViewById<TextView>(R.id.tvDescription).text = item.description
            findViewById<TextView>(R.id.tvFavorites).text = item.favorites
            findViewById<TextView>(R.id.tvListening).text = item.listening
        }
    }
}
