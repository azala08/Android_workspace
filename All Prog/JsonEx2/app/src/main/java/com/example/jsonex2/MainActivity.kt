package com.example.jsonex2

import android.os.Bundle
import android.widget.ListView
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
import org.json.JSONArray

class MainActivity : AppCompatActivity()
{
    lateinit var listView: ListView
    lateinit var list:MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list)
        list = ArrayList()

        var stringRequest = StringRequest(Request.Method.GET,"https://prakrutitech.buzz/AndroidAPI/dashboard_view.php",object:
            Response.Listener<String>{
            override fun onResponse(response: String?)
            {
                var jsonArray = JSONArray(response)
                for(i in 0 until  jsonArray.length())
                {
                    var jsonobject = jsonArray.getJSONObject(i)

                    var name = jsonobject.getString("name")
                    var image = jsonobject.getString("url")

                    var m = Model()
                    m.name=name
                    m.url=image

                    list.add(m)
                }

                var adapter = MyAdapter(applicationContext,list)
                listView.adapter=adapter

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