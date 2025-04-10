package com.example.meditationapp.Activity

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meditationapp.R

class ReminderActivity : AppCompatActivity() {

    private lateinit var timePicker: TimePicker
    private lateinit var btnSave: Button
    private lateinit var tvNoThanks: TextView
    private lateinit var dayButtonsContainer: LinearLayout

    private val selectedDays = mutableSetOf<String>()
    private val days = listOf("SU", "M", "T", "W", "TH", "F", "S")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        timePicker = findViewById(R.id.timePicker)
        btnSave = findViewById(R.id.btnSave)
        tvNoThanks = findViewById(R.id.tvNoThanks)
        dayButtonsContainer = findViewById(R.id.dayButtonsContainer)

        setupDayButtons()
        timePicker = findViewById(R.id.timePicker)
        timePicker.setIs24HourView(false) // ✅ Set 12-hour format here
        btnSave.setOnClickListener {
            val hour = timePicker.hour
            val minute = timePicker.minute

            val amPm = if (hour < 12) "AM" else "PM"
            val displayHour = if (hour % 12 == 0) 12 else hour % 12
            val displayMinute = String.format("%02d", minute)

            val timeText = "$displayHour:$displayMinute $amPm"
            Toast.makeText(this, "Time: $timeText\nDays: $selectedDays", Toast.LENGTH_LONG).show()

            // Save or move to next screen
        }

        tvNoThanks.setOnClickListener {
            Toast.makeText(this, "No thanks clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupDayButtons() {
        for (day in days) {
            val button = TextView(this).apply {
                text = day
                setPadding(24, 24, 24, 24)
                setTextColor(Color.WHITE)
                setBackgroundResource(R.drawable.day_circle_selected)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                gravity = Gravity.CENTER
                val lp = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                lp.setMargins(8, 0, 8, 0)
                layoutParams = lp

                setOnClickListener {
                    if (selectedDays.contains(day)) {
                        selectedDays.remove(day)
                        setBackgroundResource(R.drawable.day_circle_unselected)
                        setTextColor(Color.BLACK)
                    } else {
                        selectedDays.add(day)
                        setBackgroundResource(R.drawable.day_circle_selected)
                        setTextColor(Color.WHITE)
                    }
                }
            }

            dayButtonsContainer.addView(button)
        }
    }
}
