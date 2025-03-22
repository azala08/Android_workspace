package com.example.signup

import android.content.Intent
import android.media.Image
import android.os.Bundle
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class MainActivity : AppCompatActivity()
{
    lateinit var email : EditText
    lateinit var pwd : EditText
    lateinit var cpwd : EditText
    lateinit var reg : Button
    lateinit var txt : TextView
    lateinit var img : ImageView
    lateinit var auth : FirebaseAuth
    lateinit var db:FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.edtemail)
        pwd = findViewById(R.id.edtpwd)
        cpwd = findViewById(R.id.edtcpwd)
        reg = findViewById(R.id.btnsignup)
        txt = findViewById(R.id.txt1)
        img = findViewById(R.id.imgreg)
        db = FirebaseDatabase.getInstance()


        auth = Firebase.auth

        txt.setOnClickListener {
            var i = Intent(applicationContext,LoginActivity::class.java)
            startActivity(i)
        }

        reg.setOnClickListener {
            val email = email.text.toString().trim()
            val password = pwd.text.toString().trim()
            val confirmPassword = cpwd.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password == confirmPassword)
            {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful)
                        {
                            var userId = auth.currentUser?.uid
                            var user = topstech2(email, password)

                            db.getReference("topstech2")
                                .child(userId!!)
                                .setValue(user)
                                .addOnSuccessListener{
                                    Toast.makeText(this, "Signup Successful!", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, LoginActivity::class.java))
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "Error in storing data", Toast.LENGTH_SHORT).show()
                                }
                        }
                        else
                        {
                            Toast.makeText(this, "Signup Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        txt.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}

data class topstech2(
    val email: String,
    val password: String
)




