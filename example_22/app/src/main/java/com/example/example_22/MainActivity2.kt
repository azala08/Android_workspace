package com.example.example_22



import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Get references to the TextViews
        val textViewName = findViewById<TextView>(R.id.textViewName)
        val textViewPhone = findViewById<TextView>(R.id.textViewPhone)

        // Get the data passed from the first activity
        val name = intent.getStringExtra("NAME")
        val phone = intent.getStringExtra("PHONE")

        // Display the data in the TextViews
        textViewName.text = "Name: $name"
        textViewPhone.text = "Phone: $phone"
    }
}
