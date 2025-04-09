package com.example.meditationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TopicAdapter

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
    }
}

