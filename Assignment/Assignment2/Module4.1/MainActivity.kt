package com.example.module4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    private lateinit var btn1:Button


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1= findViewById(R.id.btn)

        btn1.setOnClickListener{

            Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()

            var i = Intent(applicationContext,Second::class.java)
            startActivity(i)

        }
    }
}