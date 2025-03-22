package com.example.listview

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Adapter as Adapter1

class MainActivity : AppCompatActivity()
{
    lateinit var listView: ListView
    lateinit var list:MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list)
        list = ArrayList()

        //set data
        list.add(Model(R.drawable.a1,"Amul Butter","45"))
        list.add(Model(R.drawable.a2,"Amul Cheese","50"))
        list.add(Model(R.drawable.b1,"Amul Chocolate","500"))
        list.add(Model(R.drawable.b2,"Amul Gold","55"))
        list.add(Model(R.drawable.a1,"Amul Cream","250"))
        list.add(Model(R.drawable.a2,"Ice Cream","450"))
        list.add(Model(R.drawable.b1,"Amul Masti","35"))
        list.add(Model(R.drawable.b2,"Amul Paneer","150"))

        var adapter = MyAdapter(applicationContext,list)
        listView.adapter=adapter
    }
}

