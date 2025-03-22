package com.example.project1.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1.Adapter.CartAdapter
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.Model.CartModel
import com.example.project1.R
import com.example.project1.databinding.ActivityCartBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityCartBinding
    private lateinit var mutableList: MutableList<CartModel>
    lateinit var apiInterface: Apiinterface
    lateinit var sharedPreferences: SharedPreferences
    lateinit var mAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Setting up ActionBar
        binding.mToolbar.title = "My Cart"
        setSupportActionBar(binding.mToolbar)
        binding.mToolbar.setNavigationOnClickListener {

            startActivity(Intent(applicationContext,DashboardActivity::class.java))


        }
        setContentView(view)
        // Setting up activity window


        mutableList = ArrayList()
        mAdapter = CartAdapter(applicationContext, mutableList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //sharedprefrence
        sharedPreferences = getSharedPreferences("PROJECT1", MODE_PRIVATE)
        val mob = sharedPreferences.getString("n1", "")


        apiInterface = ApiClient().getconnect()!!.create(Apiinterface::class.java)
        val call = apiInterface.viewcartdata(mob)
        call.enqueue(object : Callback<List<CartModel>> {
            override fun onResponse(
                call: Call<List<CartModel>>,
                response: Response<List<CartModel>>
            ) {

                //Log.d("Mydata123",response.body().toString())


                mutableList = response.body() as MutableList<CartModel>
                Toast.makeText(applicationContext, ""+mutableList.size, Toast.LENGTH_SHORT).show()

                mAdapter = CartAdapter(applicationContext, mutableList)
                binding.recyclerView.adapter = mAdapter
                //binding.progressIndicator.visibility = View.GONE

            }

            override fun onFailure(call: Call<List<CartModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
        })

    }


}