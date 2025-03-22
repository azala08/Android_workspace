package com.example.newtask1

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newtask1.database.Calculation
import com.example.newtask1.database.CalculationViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var spinner: Spinner
    private lateinit var calculateButton: Button
    private lateinit var resultText: TextView
    private lateinit var historyButton: Button
    private val viewModel: CalculationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        spinner = findViewById(R.id.spinner)
        calculateButton = findViewById(R.id.calculateButton)
        resultText = findViewById(R.id.resultText)
        historyButton = findViewById(R.id.historyButton)

        val operations = arrayOf("+", "-", "*", "/", "%")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, operations)

        calculateButton.setOnClickListener {
            val num1 = input1.text.toString().toDoubleOrNull()
            val num2 = input2.text.toString().toDoubleOrNull()
            val operation = spinner.selectedItem.toString()

            if (num1 != null && num2 != null) {
                val result = when (operation) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "*" -> num1 * num2
                    "/" -> if (num2 != 0.0) num1 / num2 else "Error"
                    "%" -> num1 % num2
                    else -> "Error"
                }
                val expression = "$num1 $operation $num2 = $result"
                resultText.text = expression
                viewModel.insert(Calculation(expression))
            } else {
                Toast.makeText(this, "Enter valid numbers", Toast.LENGTH_SHORT).show()
            }
        }

        historyButton.setOnClickListener {
            startActivity(HistoryActivity.newIntent(this))
        }
    }
}
