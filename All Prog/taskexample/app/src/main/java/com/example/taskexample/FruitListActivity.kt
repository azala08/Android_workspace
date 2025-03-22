package com.example.taskexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FruitListActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fruit_list)

    // Set up toolbar
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    // Initialize RecyclerView
    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    recyclerView.layoutManager = LinearLayoutManager(this)

    // Create sample animal data
    val animals = listOf(
        Animal("Apple", R.drawable.apple),
        Animal("Orange", R.drawable.orange),
        Animal("Mango", R.drawable.mango),
        Animal("Grapes", R.drawable.grapes),
        Animal("Cherry", R.drawable.cherry)
    )

    // Set adapter
    recyclerView.adapter = AnimalAdapter(this,animals)
}

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}