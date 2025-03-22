package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity()
{
    lateinit var f1: FloatingActionButton
    lateinit var recycler: RecyclerView
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        f1 = findViewById(R.id.f1)
        recycler = findViewById(R.id.recycler)

        var layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler.layoutManager=layoutManager

        var options = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(FirebaseDatabase.getInstance().getReference().child("topstech"),Model::class.java)
            .build()

        adapter= MyAdapter(applicationContext,options)
        recycler.adapter=adapter

        f1.setOnClickListener{
            startActivity(Intent(applicationContext,AdduserActivity::class.java))
        }
    }



    override fun onStart()
    {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onRestart() {
        super.onRestart()
        adapter.startListening()
    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()
    }


}