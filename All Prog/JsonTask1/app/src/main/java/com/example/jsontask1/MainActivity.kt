package com.example.jsontask1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.privacysandbox.tools.core.model.Method
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jsontask1.databinding.ActivityMainBinding

import com.squareup.picasso.Request
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

        var stringRequest = StringRequest(com.android.volley.Request.Method.GET,"https://simplifiedcoding.net/demos/marvel/",object:Response.Listener<String>{
            override fun onResponse(response: String?)
            {
                var jsonArray = JSONArray(response)
                for(i in 0 until jsonArray.length())
                {
                    var jsonobject = jsonArray.getJSONObject(i)

                    var name = jsonobject.getString("name")
                    var realname = jsonobject.getString("realname")
                    var team = jsonobject.getString("team")
                    var firstappereance = jsonobject.getString("firstappearance")
                    var createdby = jsonobject.getString("createdby")
                    var publisher = jsonobject.getString("publisher")
                    var imageurl = jsonobject.getString("imageurl")
                    var bio = jsonobject.getString("bio")


                    var m = Model()
                    m.name=name
                    m.realname=realname
                    m.team=team
                    m.firstappearance=firstappereance
                    m.createdby=createdby
                    m.publisher=publisher
                    m.imageurl=imageurl
                    m.bio=bio
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