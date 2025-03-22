package com.example.apnibook

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide the action bar
        supportActionBar?.hide()

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 columns

        // Create book items
        val bookItems = listOf(
            BookItem(R.drawable.client, "Client Book"),
            BookItem(R.drawable.business, "Business Book"),
            BookItem(R.drawable.stock, "Stock Book"),
            BookItem(R.drawable.expense, "Expense Book")
        )

        // Initialize adapter
        bookAdapter = BookAdapter(bookItems) { position ->
            when (position) {
                0 ->
                    startActivity(Intent(applicationContext,ClientBookActivity::class.java))//Toast.makeText(this, "Client Book tapped", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this, "Business Book tapped", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this, "Stock Book tapped", Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(this, "Expense Book tapped", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = bookAdapter

        // Initialize FAB
        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this, "Add button tapped", Toast.LENGTH_SHORT).show()
        }
    }
}