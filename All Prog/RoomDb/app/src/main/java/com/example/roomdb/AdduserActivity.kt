package com.example.roomdb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class AdduserAcitivity : AppCompatActivity()
{
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn1:Button
    lateinit var db:MyDbClass
    var id = 0
    var updatestatus=""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adduser)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btn1)
        db = Room.databaseBuilder(applicationContext, MyDbClass::class.java, "myDatabase")
            .allowMainThreadQueries()
            .build()

        if(GlobalVariables.updatestatus == "update")
        {
            id = GlobalVariables.id
            edt1.setText(GlobalVariables.name)
            edt2.setText(GlobalVariables.email)
        }

        btn1.setOnClickListener {


            if(GlobalVariables.updatestatus=="update")
            {
                var name = edt1.text.toString()
                var email = edt2.text.toString()

                var u = User()
                u.id=GlobalVariables.id
                u.name=name
                u.email=email

                db.daoClass().updatedata(u)
                Toast.makeText(applicationContext,"Updated", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }
            else{

                var name = edt1.text.toString()
                var email = edt2.text.toString()

                var user = User()
                user.name=name
                user.email=email

                db.daoClass().insertdata(user)
                Toast.makeText(applicationContext, "Inserted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }

        }
    }

}
