package com.example.signup_firebase


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var pwd: EditText
    lateinit var cpwd: EditText
    lateinit var reg: Button
    lateinit var txt: TextView
    lateinit var img: ImageView
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.edtemail)
        pwd = findViewById(R.id.edtpwd)
        cpwd = findViewById(R.id.edtcpwd)
        reg = findViewById(R.id.btnsignup)
        txt = findViewById(R.id.txt1)
        img = findViewById(R.id.imgreg)

        auth = Firebase.auth

        reg.setOnClickListener {
            signup()
        }

        txt.setOnClickListener {
            var i = Intent(applicationContext,LoginActivity::class.java)
            startActivity(i)
        }
    }

    private fun signup(){

        val email = email.text.toString().trim()
        val pass = pwd.text.toString().trim()
        val confirmPassword = cpwd.text.toString().trim()

        if (email.isEmpty() || pass.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(this, "Welcome ${user?.email}!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    }


