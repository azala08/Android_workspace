package com.example.jsonex1

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
import com.example.jsonex1.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    lateinit var list:MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList()

        var stringRequest = StringRequest(Request.Method.GET,"https://prakrutitech.buzz/Kapil/view.php",object:Response.Listener<String>{
            override fun onResponse(response: String?)
            {
                var jsonArray = JSONArray(response)
                for(i in 0 until jsonArray.length())
                {
                    var jsonobject = jsonArray.getJSONObject(i)

                    var p_name = jsonobject.getString("p_name")
                    var p_price = jsonobject.getString("p_price")
                    var p_image = jsonobject.getString("p_image")

                    var m = Model()
                    m.p_name=p_name
                    m.p_price=p_price
                    m.p_image=p_image
                    list.add(m)
                }

                var adapter = MyAdapter(applicationContext,list)
                binding.list.adapter=adapter

            }
        },object:Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?)
            {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()

            }
        })
        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringRequest)

    }
}