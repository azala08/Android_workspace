package com.example.task1

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Call_Intent : AppCompatActivity()
{
    lateinit var callnum : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_list)

        callnum=findViewById(R.id.calltxt)
        var calltxt=callnum.text.toString()

        callnum.setOnClickListener {
            var callintent = Intent(Intent.ACTION_DIAL).apply {
                data= Uri.parse("tel: $calltxt")
            }
            startActivity(callintent)
        }
    }
}

