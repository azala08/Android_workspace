package com.example.task_26feb

import android.content.Intent

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_26feb.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView:RecyclerView
    lateinit var list: MutableList<Model>
    lateinit var apiinterface: Apiinterface
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList()

        var layoutmanager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycle.layoutManager=layoutmanager

        apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)
        var call: Call<List<ProductModel>> = apiinterface.getdata()

        call.enqueue(object:Callback<List<ProductModel>>
        {
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            )
            {
                list = response.body() as MutableList<Model>

                var adapter = MyAdapter(applicationContext,list)
                binding.recycle.adapter=adapter

            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()

            }
        })

        binding.btninsert.setOnClickListener {

            startActivity(Intent(applicationContext,AdduserActivity::class.java))
        }
    }
}

