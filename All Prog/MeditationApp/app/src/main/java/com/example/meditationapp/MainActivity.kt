package com.example.meditationapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.meditationapp.Activity.ReminderActivity

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TopicAdapter
    lateinit var tvClick : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topics = listOf(
            Topic("Reduce Stress", R.color.stressCard, R.drawable.ic_stress),
            Topic("Improve Performance", R.color.performanceCard, R.drawable.ic_performance),
            Topic("Increase Happiness", R.color.happinessCard, R.drawable.ic_happiness),
            Topic("Reduce Anxiety", R.color.anxietyCard, R.drawable.ic_anxiety),
            Topic("Personal Growth", R.color.growthCard, R.drawable.ic_growth),
            Topic("Better Sleep", R.color.sleepCard, R.drawable.ic_sleep)
        )

        recyclerView = findViewById(R.id.topicRecyclerView)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = TopicAdapter(topics)
        recyclerView.adapter = adapter



        tvClick = findViewById(R.id.tvClick)
        tvClick.setOnClickListener {
            startActivity(Intent(applicationContext,ReminderActivity::class.java))
        }
    }
}

