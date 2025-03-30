package com.example.module6_3

import android.Manifest
import android.annotation.SuppressLint
import android.content.*
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private lateinit var locationTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationTextView = findViewById(R.id.locationTextView)
        startButton = findViewById(R.id.start_button)
        stopButton = findViewById(R.id.stop_button)

        checkAndRequestPermissions()

        val filter = IntentFilter("LOCATION_UPDATE")
        registerReceiver(locationReceiver, filter)

        startButton.setOnClickListener {
            if (isLocationEnabled()) {
                startLocationService()
                startButton.isEnabled = false
                stopButton.isEnabled = true
            } else {
                Toast.makeText(this, "Please enable GPS", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }

        stopButton.setOnClickListener {
            stopLocationService()
            startButton.isEnabled = true
            stopButton.isEnabled = false
        }
    }

    private fun checkAndRequestPermissions() {
        val permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                startLocationService()
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }

        val permissionsNeeded = permissions.filter {
            ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (permissionsNeeded.isNotEmpty()) {
            requestPermissionLauncher.launch(permissionsNeeded.toTypedArray())
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private val locationReceiver = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context?, intent: Intent?) {
            val latitude = intent?.getDoubleExtra("latitude", 0.0)
            val longitude = intent?.getDoubleExtra("longitude", 0.0)
            locationTextView.text = "Lat: $latitude, Lon: $longitude"
            Log.d("LocationUpdate", "Lat: $latitude, Lon: $longitude")
        }
    }

    private fun startLocationService() {
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }

    private fun stopLocationService() {
        val serviceIntent = Intent(this, MyService::class.java)
        stopService(serviceIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(locationReceiver)
    }
}