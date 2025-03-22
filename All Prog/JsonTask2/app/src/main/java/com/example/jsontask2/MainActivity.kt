package com.example.jsontask2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jsontask2.databinding.ActivityMainBinding


import com.squareup.picasso.Request
import org.json.JSONArray
import org.json.JSONObject

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

        var stringRequest = StringRequest(com.android.volley.Request.Method.GET,"https://simplifiedcoding.net/demos/marvel/",object:Response.Listener<String>{
            override fun onResponse(response: String?)
            {
                var jsonobject = JSONObject(response)
                var jsonarray = jsonobject.getJSONArray("heroes")

                //var jsonArray = JSONArray(response)

                for(i in 0 until jsonarray.length())
                {
                    var jsonobject2 = jsonarray.getJSONObject(i)

                    var name = jsonobject.getString("name")
                    var image = jsonobject.getString("imageurl")


                    var m = Model()

                    m.name=name
                    m.imageurl=image

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