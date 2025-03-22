package com.example.test_21jan

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    lateinit var toolbar: Toolbar

    lateinit var listView: GridView
    lateinit var list:MutableList<Model>
    var map = HashMap<String,Int>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar=findViewById(R.id.tool)
        setSupportActionBar(toolbar)

        listView = findViewById(R.id.grid1)
        list = ArrayList()



        list.add(Model(R.drawable.animallogo,"Animal"))
        list.add(Model(R.drawable.birdslogo,"Birds"))
        list.add(Model(R.drawable.fruitslogo,"Fruits"))
        list.add(Model(R.drawable.vegeslogo,"Vegetables"))




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
            R.id.item1->
            {
                Toast.makeText(applicationContext, "clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.item2->
            {
                Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}