package com.example.batterylevelmonitor

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val batteryReceiver = BatteryBroadcastReceiver()

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(batteryReceiver)
    }
}
