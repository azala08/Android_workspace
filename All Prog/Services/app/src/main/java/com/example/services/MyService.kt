package com.example.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService : Service()
{

    override fun onCreate()
    {
        Toast.makeText(applicationContext, "created", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int)
    {
        Toast.makeText(applicationContext, "strated", Toast.LENGTH_SHORT).show()

        for(i in 1..10)
        {
            Log.d("Data123",i.toString())
        }

        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Toast.makeText(applicationContext, "strat command", Toast.LENGTH_SHORT).show()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy()
    {
        Toast.makeText(applicationContext, "destroy", Toast.LENGTH_SHORT).show()

        super.onDestroy()
    }
    override fun onBind(intent: Intent): IBinder
    {
        TODO("Return the communication channel to the service.")
    }
}