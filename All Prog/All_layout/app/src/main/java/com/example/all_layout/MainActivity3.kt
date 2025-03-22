package com.example.all_layout

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity()
{
    lateinit var btn1:Button
    lateinit var btn2:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        //Activity to Activity
        btn1.setOnClickListener {

            var i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)

        }

        //Activity to Fragment
        btn2.setOnClickListener {

            var bm = BlankFragment()
            var fm = supportFragmentManager
            var ft = fm.beginTransaction()
            ft.replace(R.id.frmid,bm).commit()

        }
    }
}