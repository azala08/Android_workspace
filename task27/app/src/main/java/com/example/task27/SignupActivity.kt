package com.example.task27

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task27.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener
{
    private lateinit var binding: ActivitySignupBinding
    lateinit var apiinterface: Apiinterface
    var gender=" "
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)
        binding.rb1.setOnCheckedChangeListener(this)
        binding.rb2.setOnCheckedChangeListener(this)

        binding.btnsignup.setOnClickListener {

            var name = binding.edtname.text.toString()
            var sname = binding.edtsurname.text.toString()
            var email = binding.edtemail.text.toString()
            var gender2 = gender
            var mobile = binding.edtmobile.text.toString()
            var pass = binding.edtpwd.text.toString()

            var call = apiinterface.SignUp(name,sname,email,gender2,mobile,pass)

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


    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {

        if(binding.rb1.isChecked)
        {
            gender = "male"
        }
        if(binding.rb2.isChecked)
        {
            gender = "female"
        }


    }
}


