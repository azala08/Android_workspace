package com.example.task_26feb

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.ColorSpace
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task_26feb.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity()
{
    lateinit var binding : ActivityLoginBinding
    lateinit var apiinterface: Apiinterface
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)

        sharedPreferences = getSharedPreferences("MYSESSION", Context.MODE_PRIVATE)


        if(sharedPreferences.getBoolean("MYSESSION",false) && !sharedPreferences.getString("n1","")!!.isEmpty()) {
            var i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.btnlogin.setOnClickListener {

            var email = binding.edtemail.text.toString()
            var pass = binding.edtpwd.text.toString()

            var call = apiinterface.signin(email,pass)

            call.enqueue(object: Callback<ColorSpace.Model>
            {
                override fun onResponse(call: Call<ColorSpace.Model>, response: Response<ColorSpace.Model>) {

                    Toast.makeText(applicationContext, "Login Done", Toast.LENGTH_SHORT).show()
                    var xyz:SharedPreferences.Editor = sharedPreferences.edit()


                    var i = Intent(applicationContext,MainActivity::class.java)
                    xyz.putBoolean("MYSESSION",true)
                    xyz.putString("n1",email)
                    xyz.putString("p1",pass)
                    xyz.commit()
                    startActivity(i)

                    startActivity(Intent(applicationContext,MainActivity::class.java))
                }

                override fun onFailure(call: Call<ColorSpace.Model>, t: Throwable)
                {
                    Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_SHORT).show()

                }
            })
        }
        var txtsign=binding.txtsign.text.toString()
        binding.txtsign.setOnClickListener {
            startActivity(Intent(applicationContext,SignupActivity::class.java))
        }
    }
}