package com.example.searchwithlist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    lateinit var listview:ListView
    lateinit var searchView: SearchView
    lateinit var list: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        listview=findViewById(R.id.list)
        searchView=findViewById(R.id.search)
        list=ArrayList()

        list.add("Android")
        list.add("PHP")
        list.add("Java")
        list.add("Web design")
        list.add("Graphic Design")
        list.add("Flutter")
        list.add("Python")
        list.add("C++")

        var adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,list)
        listview.adapter = adapter

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean
            {
                if(list.contains(query))
                {
                    adapter.filter.filter(query)
                }

                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean
            {
                adapter.filter.filter(newText)
                return false
            }
        })
    }
}
