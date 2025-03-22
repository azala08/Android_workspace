package com.example.optionmenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity()
{

    lateinit var toolbar: Toolbar
    lateinit var btn:Button
    lateinit var btn2:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.tool)
        setSupportActionBar(toolbar)

        btn = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        registerForContextMenu(btn)

        btn2.setOnClickListener {

            var popupmenu = PopupMenu(applicationContext,btn2)
            popupmenu.menuInflater.inflate(R.menu.popup,popupmenu.menu)
            popupmenu.show()


        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.i1 ->
            {
                Toast.makeText(applicationContext, "clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        menuInflater.inflate(R.menu.context,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.i2 ->
            {
                Toast.makeText(applicationContext, "SMS SENT", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }
}