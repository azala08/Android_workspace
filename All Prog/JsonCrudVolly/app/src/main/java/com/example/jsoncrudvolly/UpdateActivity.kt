package com.example.jsoncrudvolly

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
import com.example.jsoncrudvolly.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityUpdateBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var i = intent
        var id = i.getStringExtra("id")
        var name = i.getStringExtra("name")
        var surname =  i.getStringExtra("surname")
        var email = i.getStringExtra("email")
        var pass = i.getStringExtra("password")

        binding.edtname.setText(name)
        binding.edtsurname.setText(surname)
        binding.edtemail.setText(email)
        binding.edtpass.setText(pass)


        binding.btnupdate.setOnClickListener {

            var name = binding.edtname.text.toString()
            var surname = binding.edtsurname.text.toString()
            var email = binding.edtemail.text.toString()
            var pass = binding.edtpass.text.toString()

            var stringrequest = object:
                StringRequest(Request.Method.POST,"https://prakrutitech.buzz/CRUD/update.php",object:
                    Response.Listener<String>
                {
                    override fun onResponse(response: String?)
                    {
                        Toast.makeText(applicationContext, "Updated", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                    }
                },object: Response.ErrorListener
                {
                    override fun onErrorResponse(error: VolleyError?)
                    {
                        Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
                    }
                })
            {
                override fun getParams(): MutableMap<String, String>?
                {
                    var map = HashMap<String,String>()
                    map["id"] = id.toString()
                    map["name"]=name
                    map["surname"]=surname
                    map["email"]=email
                    map["password"]=pass
                    return map
                }
            }

            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringrequest)

        }

    }
}