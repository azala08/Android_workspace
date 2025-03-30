package com.example.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity()
{
    lateinit var mail : EditText
    lateinit var pswd : EditText
    lateinit var login :Button
    lateinit var image : ImageView
    lateinit var text : TextView
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mail = findViewById(R.id.edtmail)
        pswd = findViewById(R.id.edtpass)
        login = findViewById(R.id.btnsignin)
        image = findViewById(R.id.imglogin)
        text = findViewById(R.id.txt2)
        auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            val email = mail.text.toString().trim()
            val pass = pswd.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this)
            {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Welcome ${user?.email}!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, WelcomeActivity::class.java))
                    finish()
                } else
                {
                    Log.d("MYERROR","${it.exception?.message}")
                    Toast.makeText(this, "Login Failed: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        text.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}