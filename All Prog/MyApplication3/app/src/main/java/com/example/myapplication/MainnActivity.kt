package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task2.FarmFresh
import com.example.task2.Fries
import com.example.task2.Sandwich1
import com.example.task2.Sandwich2
import com.example.task2.VegBurger
import com.example.task2.VegMakhani
import com.example.task2.Volcano
import com.example.task2.example


class MainnActivity : AppCompatActivity()
{
    //declare -object create
    private lateinit var image: ImageView
    private lateinit var abc: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task2)

        //initialize
        //connect kotlin and XML

        image = findViewById(R.id.img1)
        abc = findViewById(R.id.txt1)

        image.setOnClickListener {

            Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext,example::class.java)
            startActivity(i)
        }

        abc.setOnClickListener {

            Toast.makeText(applicationContext, "Text Clicked", Toast.LENGTH_SHORT).show()

            //Implicit Intent
            var url = "https://www.swiggy.com/restaurants"
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }



        image = findViewById(R.id.img2)
        abc = findViewById(R.id.txt2)

        image.setOnClickListener {

            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext,Sandwich2::class.java)
            startActivity(i)
        }

        abc.setOnClickListener {

            Toast.makeText(applicationContext, "Text Clicked", Toast.LENGTH_SHORT).show()

            //Implicit Intent
            var url = "https://www.swiggy.com/restaurants"
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }





        image = findViewById(R.id.img3)
        abc = findViewById(R.id.txt3)

        image.setOnClickListener {

            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext,Sandwich1::class.java)
            startActivity(i)
        }

        abc.setOnClickListener {

            Toast.makeText(applicationContext, "Text Clicked", Toast.LENGTH_SHORT).show()

            //Implicit Intent
            var url = "https://www.swiggy.com/restaurants"
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }




        image = findViewById(R.id.img4)
        abc = findViewById(R.id.txt4)

        image.setOnClickListener {

            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext,VegBurger::class.java)
            startActivity(i)
        }

        abc.setOnClickListener {

            Toast.makeText(applicationContext, "Text Clicked", Toast.LENGTH_SHORT).show()

            //Implicit Intent
            var url = "https://www.swiggy.com/restaurants"
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }




        image = findViewById(R.id.img5)
        abc = findViewById(R.id.txt5)

        image.setOnClickListener {

            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext,VegMakhani::class.java)
            startActivity(i)
        }

        abc.setOnClickListener {

            Toast.makeText(applicationContext, "Text Clicked", Toast.LENGTH_SHORT).show()

            //Implicit Intent
            var url = "https://www.swiggy.com/restaurants"
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }





        image = findViewById(R.id.img6)
        abc = findViewById(R.id.txt6)

        image.setOnClickListener {

            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext,Volcano::class.java)
            startActivity(i)
        }

        abc.setOnClickListener {

            Toast.makeText(applicationContext, "Text Clicked", Toast.LENGTH_SHORT).show()

            //Implicit Intent
            var url = "https://www.swiggy.com/restaurants"
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }




        image = findViewById(R.id.img9)
        abc = findViewById(R.id.txt9)

        image.setOnClickListener {

            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext,FarmFresh::class.java)
            startActivity(i)
        }

        abc.setOnClickListener {

            Toast.makeText(applicationContext, "Text Clicked", Toast.LENGTH_SHORT).show()

            //Implicit Intent
            var url = "https://www.swiggy.com/restaurants"
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }



        image = findViewById(R.id.img10)
        abc = findViewById(R.id.txt10)

        image.setOnClickListener {

            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext,Fries::class.java)
            startActivity(i)
        }

        abc.setOnClickListener {

            Toast.makeText(applicationContext, "Text Clicked", Toast.LENGTH_SHORT).show()

            //Implicit Intent
            var url = "https://www.swiggy.com/restaurants"
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }



    }
}