package com.example.all_layout

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    lateinit var linearLayout: LinearLayout
    lateinit var txt1:TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        linearLayout = LinearLayout(this)
        txt1 = TextView(this)
        txt1.setText("Hello")

        var width = 500
        var height = 500

        txt1.width=width
        txt1.height=height

        linearLayout.addView(txt1,width,height)
        setContentView(linearLayout)



    }
}