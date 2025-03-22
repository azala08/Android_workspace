package com.example.cashcalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cashcalculator.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity()
{
    lateinit var binding: ActivityMainBinding
    lateinit var toolbar: Toolbar


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        var view = binding.root
        setContentView(view)

        if (Build.VERSION.SDK_INT >= 21)
        {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.black)
        }

        toolbar = findViewById(R.id.tool)
        setSupportActionBar(toolbar)


        binding.imgclose.setOnClickListener {

            binding.edtdesc.text.clear()

            binding.edt500.text.clear()
            binding.edt200.text.clear()
            binding.edt100.text.clear()
            binding.edt50.text.clear()
            binding.edt20.text.clear()
            binding.edt10.text.clear()
            binding.edt5.text.clear()

            binding.TotalRs.text = "0"
            binding.NotesTotal.text = "0"

            binding.result500.text = "0"
            binding.result200.text = "0"
            binding.result100.text = "0"
            binding.result50.text = "0"
            binding.result20.text = "0"
            binding.result10.text = "0"
            binding.result5.text = "0"

            Toast.makeText(applicationContext, "All cleared", Toast.LENGTH_SHORT).show()
        }

        binding.imgsave.setOnClickListener {
            val desc = binding.edtdesc.text?.toString()?.trim()
            val total = binding.TotalRs.text?.toString()?.trim()
            val notesTotal = binding.NotesTotal.text?.toString()?.trim()
            val dateTime = binding.tvDateTime.text?.toString()?.trim()

            if (desc.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Please enter a description!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val map = hashMapOf(
                "desc" to desc,
                "total" to total,
                "notestotal" to notesTotal,
                "datetime" to dateTime
            )

            FirebaseDatabase.getInstance().reference
                .child("RecordDetail")
                .push()
                .setValue(map)
                .addOnSuccessListener {
                    Toast.makeText(this, "Inserted successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity2::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error while inserting", Toast.LENGTH_LONG).show()
                }
        }



        binding.imglist.setOnClickListener {

            Toast.makeText(applicationContext, "Saved records", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, MainActivity2::class.java))
        }

        val tvDateTime = findViewById<TextView>(R.id.tvDateTime)
        val currentTime = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        tvDateTime.text = currentTime.format(Date())

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    tvDateTime.text = currentTime.format(Date())
                }
            }
        }, 0, 1000)

        //----------------------------

        binding.edt500.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateTotal()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edt200.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateTotal()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edt100.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateTotal()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edt50.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateTotal()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edt20.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateTotal()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edt10.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateTotal()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edt5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateTotal()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        /// NotesTotal
        binding.NotesTotal.text = "${getNotesTotal()}"

    }

    fun calculateTotal() {

        binding.NotesTotal.text = "${getNotesTotal()}"

        var total = 0

        val edt500 = binding.edt500.text.toString().toIntOrNull() ?: 0
        val edt200 = binding.edt200.text.toString().toIntOrNull() ?: 0
        val edt100 = binding.edt100.text.toString().toIntOrNull() ?: 0
        val edt50 = binding.edt50.text.toString().toIntOrNull() ?: 0
        val edt20 = binding.edt20.text.toString().toIntOrNull() ?: 0
        val edt10 = binding.edt10.text.toString().toIntOrNull() ?: 0
        val edt5 = binding.edt5.text.toString().toIntOrNull() ?: 0

        total += edt500 * 500
        total += edt200 * 200
        total += edt100 * 100
        total += edt50 * 50
        total += edt20 * 20
        total += edt10 * 10
        total += edt5 * 5

        binding.TotalRs.text = total.toString()

        binding.result500.text = (edt500 * 500).toString()
        binding.result200.text = (edt200 * 200).toString()
        binding.result100.text = (edt100 * 100).toString()
        binding.result50.text = (edt50 * 50).toString()
        binding.result20.text = (edt20 * 20).toString()
        binding.result10.text = (edt10 * 10).toString()
        binding.result5.text = (edt5 * 5).toString()
    }

    fun getNotesTotal(): Int {
        var ntotal = 0

        val edt500 = binding.edt500.text.toString().toIntOrNull() ?: 0
        val edt200 = binding.edt200.text.toString().toIntOrNull() ?: 0
        val edt100 = binding.edt100.text.toString().toIntOrNull() ?: 0
        val edt50 = binding.edt50.text.toString().toIntOrNull() ?: 0
        val edt20 = binding.edt20.text.toString().toIntOrNull() ?: 0
        val edt10 = binding.edt10.text.toString().toIntOrNull() ?: 0
        val edt5 = binding.edt5.text.toString().toIntOrNull() ?: 0

        ntotal += edt500
        ntotal += edt200
        ntotal += edt100
        ntotal += edt50
        ntotal += edt20
        ntotal += edt10
        ntotal += edt5

        return ntotal
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.options,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {

            R.id.logout->
            {
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        finishAffinity()
    }

}