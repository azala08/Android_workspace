package com.example.batterylevelmonitor

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.PowerManager
import android.util.Log

class BatteryBroadcastReceiver : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)

        when {
            level > 90 -> {
                NotificationUtils.showNotification(
                    context,
                    "Battery Alert",
                    "Your charge is up to 90%"
                )
            }
            level > 47 -> {
                NotificationUtils.showNotification(
                    context,
                    "Battery Saver",
                    "You can turn off battery saver now"
                )
            }
            level > 15 -> {
                enableBatterySaver(context)
            }
        }
    }

    private fun enableBatterySaver(context: Context) {
        try {
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            val method = powerManager.javaClass.getDeclaredMethod("setPowerSaveMode", Boolean::class.java)
            method.isAccessible = true
            method.invoke(powerManager, true)
        } catch (e: Exception) {
            Log.e("BatteryReceiver", "Battery saver could not be enabled: ${e.message}")
        }
    }
}
