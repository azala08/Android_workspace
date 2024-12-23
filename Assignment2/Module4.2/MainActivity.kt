package com.example.module4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        edt3 = findViewById(R.id.edt3)
        btn = findViewById(R.id.btn1)

        btn.setOnClickListener {

            var name = edt1.text.toString()
            var email = edt2.text.toString()
            var phno = edt3.text.toString()

            if(name.length==0 && email.length==0 && phno.length==0)
            {
                edt1.setError("Please Enter Name")
                edt2.setError("Please Enter E-mail")
                edt3.setError("Please Enter Phone no")
            }
            else if(name.length==0)
            {
                edt1.setError("Please Enter Name")
            }
            else if(email.length==0)
            {
                edt2.setError("Please Enter Password")
            }
            else if(phno.length==0)
            {
                edt3.setError("Please Enter Phone no")
            }
            else
            {
                if(name.equals("tops") && email.equals("abc@gmail.com") && phno.equals("1234567890"))
                {
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}