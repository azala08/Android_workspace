package com.example.lifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext,"Created",Toast.LENGTH_SHORT).show()

    }

    override fun onStart()
    {
        Toast.makeText(applicationContext, "Started", Toast.LENGTH_SHORT).show()
        super.onStart()
    }

    override fun onPause() {
        Toast.makeText(applicationContext, "Pause", Toast.LENGTH_SHORT).show()
        super.onPause()
    }

    override fun onResume() {
        Toast.makeText(applicationContext, "Resume", Toast.LENGTH_SHORT).show()
        super.onResume()
    }

    override fun onRestart() {
        Toast.makeText(applicationContext, "Restart", Toast.LENGTH_SHORT).show()
        super.onRestart()
    }

    override fun onDestroy() {
        Toast.makeText(applicationContext, "Destroyed", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    override fun onStop() {
        Toast.makeText(applicationContext, "Stopped", Toast.LENGTH_SHORT).show()
        super.onStop()
    }
    }
}