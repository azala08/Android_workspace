package com.example.database1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewActivity : AppCompatActivity()
{
    lateinit var listview:ListView
    lateinit var list:MutableList<Model>
    var arrayList:ArrayList<HashMap<String,String>> = ArrayList()
    lateinit var dbHelper: DbHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        listview = findViewById(R.id.list)
        list = ArrayList()
        dbHelper = DbHelper(applicationContext)
        list = dbHelper.viewdata()


        for(i in list)
        {
            var hm = HashMap<String,String>()
            hm["name"] = i.name
            hm["number"] = i.num

            arrayList.add(hm)
        }

        var from = arrayOf("name","number")
        var to = intArrayOf(R.id.txt1,R.id.txt2)

        var adapter = SimpleAdapter(applicationContext,arrayList,R.layout.design,from,to)
        listview.adapter=adapter

    }
}