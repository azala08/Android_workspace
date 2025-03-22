package com.example.taskexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar

class AnimalListActivity : AppCompatActivity() {

    private var animalAdapter: AnimalAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)

        // Set up toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create sample animal data
        val animals = listOf(
            Animal("Lion", R.drawable.lion),
            Animal("Deer", R.drawable.deer),
            Animal("Cat", R.drawable.cat),
            Animal("Tiger", R.drawable.tiger),
            Animal("Dog", R.drawable.dog)
        )

        // Set adapter with context
        animalAdapter = AnimalAdapter(this, animals)
        recyclerView.adapter = animalAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        animalAdapter?.cleanup()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}





