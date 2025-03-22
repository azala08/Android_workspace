package com.example.roomdb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity()
{
    lateinit var f1:FloatingActionButton
    lateinit var recyclerView: RecyclerView
    lateinit var list:MutableList<User>
    lateinit var db:MyDbClass

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        f1 = findViewById(R.id.f1)

        recyclerView = findViewById(R.id.recycler)
        list = ArrayList()
        db = Room.databaseBuilder(applicationContext, MyDbClass::class.java, "myDatabase")
            .allowMainThreadQueries()
            .build()

        var layoutmanager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager=layoutmanager

        list = db.daoClass().viewdata()

        var adapter = MyAdapter(applicationContext,list)
        recyclerView.adapter=adapter

        f1.setOnClickListener {

            startActivity(Intent(applicationContext,AdduserAcitivity::class.java))
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}