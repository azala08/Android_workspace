package com.example.task2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R

class TestActivity : AppCompatActivity() {

    //declare -object create
     lateinit var image: ImageView
     lateinit var abc: TextView
     lateinit var image2 : ImageView
     lateinit var abc2 : TextView




    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.task2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        image = findViewById(R.id.img1)
        abc = findViewById(R.id.txt1)

        image.setOnClickListener {

            Toast.makeText(applicationContext,"Clicked", Toast.LENGTH_LONG).show()

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