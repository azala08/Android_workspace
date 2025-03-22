package com.example.ratrofit2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ratrofit2.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity()
{
    private lateinit var binding: ActivitySignupBinding
    lateinit var apiinterface: Apiinterface

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)

        binding.btnsignup.setOnClickListener {

            var fname = binding.edtfname.text.toString()
            var email = binding.edtemail.text.toString()
            var pass = binding.edtpass.text.toString()

            var call = apiinterface.signup(fname,email,pass)

            call.enqueue(object: Callback<Void>
            {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                    Toast.makeText(applicationContext, "Registration Done", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,LoginActivity::class.java))
                }

                override fun onFailure(call: Call<Void>, t: Throwable)
                {
                    Toast.makeText(applicationContext, "Registration Fail", Toast.LENGTH_SHORT).show()

                }
            })

        }


    }
}