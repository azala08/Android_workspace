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
import com.example.project1.Adapter.WishlistAdapter
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.Model.WishlistModel
import com.example.project1.R
import com.example.project1.databinding.ActivityWishlistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityWishlistBinding
    private lateinit var mutableList: MutableList<WishlistModel>
    lateinit var apiInterface: Apiinterface
    lateinit var sharedPreferences: SharedPreferences
    lateinit var mAdapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        val view = binding.root
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Setting up ActionBar
        binding.mToolbar.title = "My Wishlist"
        setSupportActionBar(binding.mToolbar)
        binding.mToolbar.setNavigationOnClickListener {

            startActivity(Intent(applicationContext,DashboardActivity::class.java))


        }
        setContentView(view)
        // Setting up activity window


        mutableList = ArrayList()
        mAdapter = WishlistAdapter(applicationContext, mutableList)
        binding.recycler.layoutManager = LinearLayoutManager(this)

        //sharedprefrence
        sharedPreferences = getSharedPreferences("Appproject", MODE_PRIVATE)
        val mob = sharedPreferences.getString("n1", "")


        apiInterface = ApiClient().getconnect()!!.create(Apiinterface::class.java)
        val call = apiInterface.viewwishlistdata(mob)
        call.enqueue(object : Callback<List<WishlistModel>> {
            override fun onResponse(
                call: Call<List<WishlistModel>>,
                response: Response<List<WishlistModel>>
            ) {

                //Log.d("Mydata123",response.body().toString())


                mutableList = response.body() as MutableList<WishlistModel>
                Toast.makeText(applicationContext, ""+mutableList.size, Toast.LENGTH_SHORT).show()

                mAdapter = WishlistAdapter(applicationContext, mutableList)
                binding.recycler.adapter = mAdapter
                //binding.progressIndicator.visibility = View.GONE

            }

            override fun onFailure(call: Call<List<WishlistModel>>, t: Throwable) {
                Toast.makeText(this@WishlistActivity, "Failed", Toast.LENGTH_SHORT).show()
            }
        })

    }


}