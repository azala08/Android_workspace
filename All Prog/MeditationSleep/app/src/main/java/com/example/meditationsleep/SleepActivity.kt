package com.example.meditationsleep

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SleepActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SleepAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep)

        recyclerView = findViewById(R.id.rvSleep)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val items = listOf(
            SleepItem("Night Island", "45 MIN", "SLEEP MUSIC", R.drawable.night_island),
            SleepItem("Sweet Sleep", "45 MIN", "SLEEP MUSIC", R.drawable.sweet_sleep),
            SleepItem("Good Night", "45 MIN", "SLEEP MUSIC", R.drawable.goodnight),
            SleepItem("Moon Clouds", "45 MIN", "SLEEP MUSIC", R.drawable.moon_cloud),
            SleepItem("Night Island", "45 MIN", "SLEEP MUSIC", R.drawable.night_island),
            SleepItem("Sweet Sleep", "45 MIN", "SLEEP MUSIC", R.drawable.sweet_sleep),
            SleepItem("Good Night", "45 MIN", "SLEEP MUSIC", R.drawable.goodnight),
            SleepItem("Moon Clouds", "45 MIN", "SLEEP MUSIC", R.drawable.moon_cloud)
        )

        adapter = SleepAdapter(items) { item ->
            val intent = Intent(this, PlayOptionActivity::class.java)
            intent.putExtra("musicItem", item)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}
