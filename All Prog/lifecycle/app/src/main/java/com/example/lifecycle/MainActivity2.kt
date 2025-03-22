package com.example.lifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity()
{
    lateinit var edt1: EditText
    lateinit var btn1: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        edt1 = findViewById(R.id.edt1)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener{

            var name = edt1.text.toString()
            var i = Intent(applicationContext,MainActivity3::class.java)
            i.putExtras("n1",name)
            startActivity(i)
        }


    }
}


