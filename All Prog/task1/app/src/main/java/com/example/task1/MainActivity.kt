package com.example.task1

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var listview: ListView
    lateinit var list: MutableList<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_list)

        listview = findViewById(R.id.list)

        list = ArrayList()

        list.add("1234567890")
        list.add("9876543210")
        list.add("5555555555")
        list.add("4444444444")
        list.add("3333333333")

        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, list)
        listview.adapter = adapter

        // Set item click listener for the ListView
        listview.setOnItemClickListener { _, _, position, _ ->
            val selectedNumber = list[position] // Get the clicked phone number
            val callIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$selectedNumber")
            }
            startActivity(callIntent)
        }
    }
}
