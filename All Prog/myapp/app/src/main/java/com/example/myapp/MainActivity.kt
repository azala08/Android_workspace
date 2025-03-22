package com.example.myapp


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main   )

        // Find views by ID
        val etStartValue: EditText = findViewById(R.id.et_start_value)
        val etEndValue: EditText = findViewById(R.id.et_end_value)
        val btnCalculate: Button = findViewById(R.id.btn_calculate)
        val tvResult: TextView = findViewById(R.id.tv_result)

        // Set click listener for the button
        btnCalculate.setOnClickListener {
            // Get values from the EditTexts
            val startValueStr = etStartValue.text.toString()
            val endValueStr = etEndValue.text.toString()

            // Validate input
            if (startValueStr.isEmpty() || endValueStr.isEmpty()) {
                tvResult.text = "Please enter both values."
                return@setOnClickListener
            }

            // Parse to integers
            val startValue = startValueStr.toIntOrNull()
            val endValue = endValueStr.toIntOrNull()

            // Validate the parsed integers
            if (startValue == null || endValue == null) {
                tvResult.text = "Please enter valid numbers."
                return@setOnClickListener
            }

            // Validate the range
            if (startValue > endValue) {
                tvResult.text = "Starting value must be less than or equal to ending value."
                return@setOnClickListener
            }

            // Generate numbers in the range
            val result = (startValue..endValue).joinToString(" ")

            // Display the result
            tvResult.text = result
        }
    }
}
