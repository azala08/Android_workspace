package com.example.jsoninsert

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
import com.example.jsoninsert.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity2 : AppCompatActivity()
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

        var stringRequest = StringRequest(com.android.volley.Request.Method.GET,"https://prakrutitech.buzz/Fluttertestapi/productview.php",object: Response.Listener<String>{
            override fun onResponse(response: String?)
            {
                var jsonArray = JSONArray(response)
                for(i in 0 until jsonArray.length())
                {
                    var jsonobject = jsonArray.getJSONObject(i)

                    var id = jsonobject.getString("id")
                    var name = jsonobject.getString("product_name")
                    var price = jsonobject.getString("product_price")
                    var description = jsonobject.getString("product_description")

                    var m = Model()

                    m.id=id
                    m.product_name=name
                    m.product_price=price
                    m.product_description=description

                    list.add(m)
                }

                var adapter = MyAdapter(applicationContext,list)
                binding.list.adapter = adapter


            }
        },object: Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?)
            {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()

            }
        })
        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringRequest)

    }
}