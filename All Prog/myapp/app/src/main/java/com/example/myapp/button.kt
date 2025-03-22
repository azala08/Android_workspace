package com.example.myapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class Button : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        // Find the button by ID
        val button: Button = findViewById(R.id.btn_change_color)

        // Set an OnClickListener to change the button color
        button.setOnClickListener {
            // Generate a random boolean
            val isGreen = Random.nextBoolean()

            // Change the button background color
            if (isGreen) {
                button.setBackgroundColor(Color.GREEN)
            } else {
                button.setBackgroundColor(Color.YELLOW)
            }
        }
    }
}
