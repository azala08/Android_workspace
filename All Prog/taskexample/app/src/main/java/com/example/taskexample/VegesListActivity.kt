package com.example.taskexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VegesListActivity : AppCompatActivity()
{

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_veges_list)

        // Set up toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create sample animal data
        val animals = listOf(
            Animal("Capsicum", R.drawable.capsicum),
            Animal("Tomato", R.drawable.tomato),
            Animal("Onion", R.drawable.onion),
            Animal("Cabbage", R.drawable.cabbage),
            Animal("Potato", R.drawable.potato)
        )

        // Set adapter
        recyclerView.adapter = AnimalAdapter(this,animals)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}