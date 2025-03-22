package com.example.test_21jan

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Fruits : AppCompatActivity()
{

    lateinit var listView: ListView
    lateinit var list:MutableList<Model>
    var map = HashMap<String,Int>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruits)

        listView = findViewById(R.id.list3)
        list = ArrayList()

        list.add(Model(R.drawable.lion,"Lion"))
        list.add(Model(R.drawable.tiger,"Tiger"))
        list.add(Model(R.drawable.dog,"Dog"))
        list.add(Model(R.drawable.cat,"Cat"))
        list.add(Model(R.drawable.deer,"Deer"))

        var adapter = MyAdapter(applicationContext,list)
        listView.adapter=adapter

    }
}