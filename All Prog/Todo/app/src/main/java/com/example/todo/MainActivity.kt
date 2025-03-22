package com.example.todo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var btn: Button
    lateinit var dbhelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn = findViewById(R.id.btnTask)
        dbhelper = DbHelper(applicationContext)

        loadData()

        btn.setOnClickListener {
            val task = edt1.text.toString()
            val disc = edt2.text.toString()

            if (task.isNotEmpty() && disc.isNotEmpty()) {
                val m = Model()
                m.Tname = task
                m.Tdisc = disc

                dbhelper.insertdata(m)
                Toast.makeText(applicationContext, "Task Inserted Successfully", Toast.LENGTH_SHORT).show()

                edt1.text.clear()
                edt2.text.clear()

                loadData()
            } else {
                Toast.makeText(applicationContext, "Please enter task details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadData() {
        val f1 = DesignFragment()
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fl, f1).commit()
    }
}