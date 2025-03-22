package com.example.signup_firebase

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity()
{
    lateinit var mail:EditText
    lateinit var login:Button
    lateinit var text: EditText
    lateinit var img1 :ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mail = findViewById(R.id.edtpass)
        login = findViewById(R.id.btnsignin)
        text = findViewById(R.id.txt2)
        img1 = findViewById(R.id.imglogin)





    }
}