package com.example.task_26feb

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task_26feb.databinding.ActivitySignupBinding
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

            var id = binding.edtid.text.toString()
            var name = binding.edtname.text.toString()
            var surname = binding.edtsurname.text.toString()
            var email = binding.edtemail.text.toString()
            var gender = binding.edtgender.text.toString()
            var mobil = binding.edtmobile.text.toString()
            var password = binding.edtpwd.text.toString()

            var call = apiinterface.signup(id,name,surname,email,gender,mobil,password)

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