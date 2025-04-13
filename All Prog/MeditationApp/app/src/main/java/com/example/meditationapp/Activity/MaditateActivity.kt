package com.example.meditationapp.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.meditationapp.Adapter.MeditateAdapter
import com.example.meditationapp.Model.Meditate
import com.example.meditationapp.R


class MaditateActivity : AppCompatActivity() {

    private val categories = listOf(
        Meditate("All", R.drawable.heart,""),
        Meditate("My", R.drawable.heart,""),
        Meditate("Anxious", R.drawable.heart,""),
        Meditate("Sleep", R.drawable.heart,""),
        Meditate("Kids", R.drawable.heart,"")
    )

    private val meditations = listOf(
        Meditate("",R.drawable.ic_stress, "7 Days of Calm"),
        Meditate("",R.drawable.ic_happiness, "Anxiety Release"),
        Meditate("",R.drawable.ic_sleep, "Meditation 3"),
        Meditate("",R.drawable.ic_anxiety, "Meditation 4"),
        Meditate("",R.drawable.ic_growth, "Meditation 5"),
        Meditate("",R.drawable.ic_performance, "Meditation 6")
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categoryRecyclerView: RecyclerView = findViewById(R.id.categoryRecyclerView)
        categoryRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryRecyclerView.adapter = MeditateAdapter(categories)

        val meditationRecyclerView: RecyclerView = findViewById(R.id.meditationRecyclerView)
        meditationRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        meditationRecyclerView.adapter = MeditateAdapter(meditations)
    }
}