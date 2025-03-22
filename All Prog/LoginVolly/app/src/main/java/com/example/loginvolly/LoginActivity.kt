package com.example.loginvolly

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.loginvolly.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity()
{
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnlogin.setOnClickListener {

            var email = binding.edtemail.text.toString()
            var pass = binding.edtpass.text.toString()

            var stringRequest = object :
                StringRequest(Request.Method.POST, "https://prakrutitech.buzz/Fluttertestapi/signin.php",object:
                    Response.Listener<String> {
                    override fun onResponse(response: String?)
                    {
                        if(response!!.trim().equals("0"))
                        {
                            Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()

                        }
                    }

                }, object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?)
                    {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }
                })
            {
                override fun getParams(): MutableMap<String, String>?
                {
                    var map = HashMap<String,String>()

                    map["email"] = email
                    map["password"] = pass
                    return map
                }
            }
            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)


        }

    }
}