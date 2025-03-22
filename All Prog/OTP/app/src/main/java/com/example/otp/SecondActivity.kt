package com.example.otp

import android.annotation.SuppressLint
import android.graphics.ColorSpace
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity()
{
    lateinit var edt1:EditText
    lateinit var btn1:Button
    lateinit var apiinterface: Apiinterface

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        edt1 = findViewById(R.id.edt1)
        btn1 = findViewById(R.id.btn1)

        var i = intent
        var email = i.getStringExtra("myemail").toString()

        apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)


        btn1.setOnClickListener {

            var call: Call<ColorSpace.Model> = apiinterface.verifyotp(email)

            call.enqueue(object:Callback<Model>
            {
                override fun onResponse(call: Call<Model>, response: Response<Model>) {

                    Toast.makeText(applicationContext, "Success: "+response.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("MYRESP",response.toString())
                }

                override fun onFailure(call: Call<Model>, t: Throwable) {
                    Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()                }
            })
        }
    }
}