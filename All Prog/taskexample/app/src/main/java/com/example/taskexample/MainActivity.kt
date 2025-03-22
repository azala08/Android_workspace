package com.example.taskexample

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Set up toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Hide the default title

        // Set up click listeners for cards
        setupCardClickListeners()
    }

    private fun setupCardClickListeners() {
        // Animals card click listener
        findViewById<CardView>(R.id.cardAnimals).setOnClickListener {
            // Start AnimalListActivity when Animals card is clicked
            startActivity(Intent(this, AnimalListActivity::class.java))
        }

        // Birds card click listener
        findViewById<CardView>(R.id.cardBirds).setOnClickListener {
            // Start AnimalListActivity when Animals card is clicked
            startActivity(Intent(this, BirdListActivity::class.java))
        }

        // Fruits card click listener
        findViewById<CardView>(R.id.cardFruits).setOnClickListener {
            // Start AnimalListActivity when Animals card is clicked
            startActivity(Intent(this, FruitListActivity::class.java))
        }

        // Vegetables card click listener
        findViewById<CardView>(R.id.cardVegetables).setOnClickListener {
            // Start AnimalListActivity when Animals card is clicked
            startActivity(Intent(this, VegesListActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu resource
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.shareapp -> {
                var shareContent = "Share App"
                var shareAppIntent = Intent(Intent.ACTION_SEND).apply {

                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareContent)
                }
                startActivity(Intent.createChooser(shareAppIntent, "Share Via"))
            }
        }

        when (item.itemId) {
            R.id.calldial -> {
                // Handle call action
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:7820080017") // Replace with your desired phone number
                }
                startActivity(intent)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        //
        //finishAffinity()
        //dialog

        var alert = AlertDialog.Builder(this)
        alert.setTitle("Are you sure you want to exit?")
        alert.setPositiveButton("YES", { dialogInterface: DialogInterface, i: Int ->

            finishAffinity()
        })
        alert.setNegativeButton("NO", { dialogInterface: DialogInterface, i: Int ->

            dialogInterface.cancel()
        })
        alert.show()


    }
}
