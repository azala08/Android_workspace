package com.example.searchwithlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    lateinit var listview: ListView
    lateinit var list: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        listview = findViewById(R.id.list)
        list = ArrayList()

        list.add("1234567890")
        list.add("9876543210")
        list.add("5555555555")
        list.add("4444444444")
        list.add("3333333333")

        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, list)
        listview.adapter = adapter
        var call_ecmi = null
        var callnum = call_ecmi.pos

        // Set item click listener for the ListView
        listview.setOnClickListener {
            var callintent = Intent(Intent.ACTION_DIAL).apply {
                data= Uri.parse("tel: $list[pos]")
            }
            startActivity(callintent)
        }
    }
}
