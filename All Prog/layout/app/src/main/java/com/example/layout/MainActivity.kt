package com.example.layout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    lateinit var btn1:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {

            Toast.makeText(applicationContext, "OK", Toast.LENGTH_SHORT).show()
            var alert = AlertDialog.Builder(this)
            var layout = LayoutInflater.from(applicationContext)
            var view = layout.inflate(R.layout.design,null)
            alert.setView(view)
            alert.show()

        }

    }
}