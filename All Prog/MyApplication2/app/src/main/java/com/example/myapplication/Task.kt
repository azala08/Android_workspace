package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Task : AppCompatActivity() {

    //declare object
    private lateinit var img1: ImageView
    private lateinit var txt1: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //initialize  //kotlin and xml
        img1 = findViewById(R.id.img1)
        txt1 = findViewById(R.id.txt1)

        img1.setOnClickListener()
        {

            Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_LONG).show()
            var i = Intent(applicationContext, TestActivity::class.java)
            startActivity(i)

        }
        txt1.setOnClickListener {

            Toast.makeText(applicationContext, "Text Clicked", Toast.LENGTH_SHORT).show()

            //Implicit Intent
            var url = "https://www.geeksforgeeks.org/android-tutorial/"
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }

        setContentView(R.layout.activity_task2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}