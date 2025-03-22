package com.example.example_22


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to the EditText fields and the Button
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val buttonSend = findViewById<Button>(R.id.buttonSend)

        // Set the OnClickListener for the button
        buttonSend.setOnClickListener {
            // Get the text entered by the user
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()

            // Create an Intent to start the second activity
            val intent = Intent(this, MainActivity2::class.java)
            // Add the data to the intent
            intent.putExtra("NAME", name)
            intent.putExtra("PHONE", phone)
            // Start the second activity
            startActivity(intent)
        }
    }
}
