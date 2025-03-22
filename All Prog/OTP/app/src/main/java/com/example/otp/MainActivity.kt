package com.example.otp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity()
{
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn1:Button
    lateinit var apiinterface: Apiinterface

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1 = findViewById(R.id.fname)
        edt2 = findViewById(R.id.email)
        btn1 = findViewById(R.id.btn1)

        apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)

        btn1.setOnClickListener {

            var name = edt1.text.toString()
            var email = edt2.text.toString()

          var call:Call<Void> = apiinterface.emailsend(name,email)
        }

    }
}