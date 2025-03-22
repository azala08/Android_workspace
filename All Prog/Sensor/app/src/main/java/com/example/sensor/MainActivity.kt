package com.example.sensor

import android.annotation.SuppressLint
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(),SensorEventListener
{
    lateinit var txt1 : TextView
    lateinit var manager: SensorManager
    private var color = false
    private var lastUpdate : Long =0

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt1 = findViewById(R.id.txt1)
        txt1.setBackgroundColor(R.color.PINK)

        manager = getSystemService(SENSOR_SERVICE) as SensorManager

        lastUpdate = System.currentTimeMillis()

    }

    override fun onSensorChanged(p0: SensorEvent?)
    {
        if (p0!!.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            getAccelerometer(p0)
        }
    }

    private fun getAccelerometer(p0: SensorEvent)
    {
        val values: FloatArray = p0.values

        val x = values[0]
        val y = values[1]
        val z = values[2]

        val accelationSquareRoot = ((x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH))
        val actualTime: Long = p0.timestamp

        if (accelationSquareRoot >= 2)
        {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            Toast.makeText(this, "Device was shuffled", Toast.LENGTH_SHORT)
                .show();
            if (color)
            {
                txt1.setBackgroundColor(Color.BLUE)
            }

            else
            {
                txt1.setBackgroundColor(Color.CYAN)
            }
            color = !color
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int)
    {

    }

    override fun onResume()
    {
        super.onResume()
        manager.registerListener(this,
            manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL);
    }

    override fun onPause()
    {
        super.onPause()
        manager.registerListener(this,
            manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL);
    }
}