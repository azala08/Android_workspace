package com.example.project1.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity()
{
    lateinit var apiinterface: Apiinterface
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_test)

        Thread.sleep(1000)

        sharedPreferences = getSharedPreferences("Appproject", Context.MODE_PRIVATE)
        Toast.makeText(applicationContext,"Welcome "+sharedPreferences.getString("mob",""), Toast.LENGTH_LONG).show()

        var mob = sharedPreferences.getString("n1","").toString()
        val intent = intent
        var id=intent.getIntExtra("id",404)
        var pName = intent.getStringExtra("pName").toString()
        var pPrice = intent.getStringExtra("pPrice").toString()
        var pDesc = intent.getStringExtra("pDesc").toString()
        var pImage = intent.getStringExtra("pImage").toString()

        apiinterface = ApiClient().getconnect().create(Apiinterface::class.java)
        var call: Call<Void> = apiinterface.paymentadd(pName,pPrice,pDesc,pImage,mob)

        //  Log.d("TOPSTOPS","Success2")
        call.enqueue(object: Callback<Void>
        {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                // Log.d("TOPSTOPS1",response.toString())
                startActivity(Intent(applicationContext,CartActivity::class.java))

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

                Log.d("TOPSTOPS2",t.message.toString())
            }
        })
    }
}