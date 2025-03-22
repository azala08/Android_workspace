package com.example.a18_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity()
{
    lateinit var s1: Switch
    lateinit var edt1: EditText
    lateinit var btn1: Button
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        s1 = findViewById(R.id.s1)
        edt1 = findViewById(R.id.edt1)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {

            var e = edt1.text.toString()
            if(isValidEmail(e))
            {
                Toast.makeText(applicationContext, "Email id is perfect", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext, "Email id is not OK", Toast.LENGTH_SHORT).show()

            }

        }

        s1.setOnCheckedChangeListener(this)
    }
    fun isValidEmail(email: String): Boolean
    {
        return email.matches(emailPattern.toRegex())
    }

    fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean)
    {
        if(s1.isChecked)
        {
            Toast.makeText(applicationContext, "SWITCH IS ON", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(applicationContext, "SWITCH IS OFF", Toast.LENGTH_SHORT).show()

        }
    }
}
