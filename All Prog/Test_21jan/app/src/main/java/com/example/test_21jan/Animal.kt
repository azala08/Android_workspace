package com.example.test_21jan

import android.os.Bundle
import android.widget.GridView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Animal : AppCompatActivity()
{

    lateinit var listView: ListView
    lateinit var list:MutableList<Model>
    var map = HashMap<String,Int>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)
        listView = findViewById(R.id.list1)
        list = ArrayList()

        list.add(Model(R.drawable.lion,"Lion"))
        list.add(Model(R.drawable.tiger,"Tiger"))
        list.add(Model(R.drawable.dog,"Dog"))
        list.add(Model(R.drawable.cat,"Cat"))
        list.add(Model(R.drawable.deer,"Deer"))



    }
}