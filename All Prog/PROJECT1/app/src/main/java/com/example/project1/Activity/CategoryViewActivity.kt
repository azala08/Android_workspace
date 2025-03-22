package com.example.project1.Activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.telecom.Call
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Adapter.CategoryAdapter
import com.example.project1.Adapter.CategoryView
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.Model.CategoryModel
import com.example.project1.R
import com.example.project1.databinding.ActivityCategoryViewBinding
import retrofit2.Callback
import retrofit2.Response

class CategoryViewActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityCategoryViewBinding
    lateinit var apiinterface: Apiinterface
    lateinit var categorylist:MutableList<CategoryModel>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (Build.VERSION.SDK_INT >= 21)
        {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.white)
        }
        apiinterface = ApiClient().getconnect().create(Apiinterface::class.java)

        var layoutmanager: RecyclerView.LayoutManager = GridLayoutManager(this,2)
        binding.categoryrecycler.layoutManager=layoutmanager

        var i = intent
        var id = i.getIntExtra("did",404)
        var name = i.getStringExtra("dname")



        Toast.makeText(applicationContext, ""+id, Toast.LENGTH_SHORT).show()

        var call = apiinterface.categoryviewdata(id)
        call.enqueue(object: Callback<List<CategoryModel>>
        {
            override fun onResponse(
                call: retrofit2.Call<List<CategoryModel>>,
                response: Response<List<CategoryModel>>
            ) {
                categorylist = response.body() as MutableList<CategoryModel>
                //Log.d("11",categorylist.size.toString())
                //Log.d("12",response.body().toString())
                var adapter = CategoryAdapter(applicationContext,categorylist)
                binding.categoryrecycler.adapter=adapter
            }

            override fun onFailure(call: retrofit2.Call<List<CategoryModel>>, t: Throwable)
            {
                Toast.makeText(applicationContext, "No Internet"+t.message, Toast.LENGTH_SHORT).show()
                //Log.d("MYERROR",t.message.toString())
            }

        })
    }
}