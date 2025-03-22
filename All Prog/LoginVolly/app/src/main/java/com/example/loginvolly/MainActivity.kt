package com.example.loginvolly

import android.content.Intent
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
import com.example.loginvolly.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnsignup.setOnClickListener {

            var name = binding.edtfname.text.toString()
            var email = binding.edtemail.text.toString()
            var pass = binding.edtpass.text.toString()

            var stringRequest = object :
                StringRequest(Request.Method.POST, "https://prakrutitech.buzz/Fluttertestapi/signup.php",object:
                    Response.Listener<String> {
                    override fun onResponse(response: String?)
                    {
                        Toast.makeText(applicationContext, "Registration Done", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext,LoginActivity::class.java))
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
                    map["firstname"] = name
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