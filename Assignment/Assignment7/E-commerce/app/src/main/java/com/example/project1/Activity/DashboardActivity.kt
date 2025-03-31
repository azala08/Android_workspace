package com.example.project1.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Adapter.DashboardAdapter
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.Model.DashboardModel
import com.example.project1.R
import com.example.project1.databinding.ActivityDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityDashboardBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var apiinterface: Apiinterface
    lateinit var dashboardlist:MutableList<DashboardModel>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("Appproject", Context.MODE_PRIVATE)
        binding.dashboardtoolbar.setTitle("WELCOME "+sharedPreferences.getString("n1","NameError"))
        setSupportActionBar(binding.dashboardtoolbar)
        if (Build.VERSION.SDK_INT >= 21)
        {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.white)
        }

        var layoutmanager:RecyclerView.LayoutManager = GridLayoutManager(this,2)
        binding.dashboardrecycler.layoutManager=layoutmanager

        apiinterface = ApiClient().getconnect().create(Apiinterface::class.java)

        var call: Call<List<DashboardModel>> = apiinterface.dashboardviewdata()
        call.enqueue(object:Callback<List<DashboardModel>>
        {
            override fun onResponse(
                call: Call<List<DashboardModel>>,
                response: Response<List<DashboardModel>>)
            {

                dashboardlist = response.body() as MutableList<DashboardModel>

                var adapter = DashboardAdapter(applicationContext,dashboardlist)
                binding.dashboardrecycler.adapter=adapter

            }

            override fun onFailure(call: Call<List<DashboardModel>>, t: Throwable) {

                Toast.makeText(applicationContext, "No Internet"+t.message, Toast.LENGTH_SHORT).show()
                //Log.d("MYERROR",t.message.toString())
            }
        })


    }
    override fun onBackPressed() {
        //super.onBackPressed()

        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Are you sure you want to exit?")
        alertDialog.setPositiveButton("YES",{ dialogInterface: DialogInterface, i: Int ->

            finishAffinity()
        })
        alertDialog.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int ->
            dialogInterface.cancel()
        })
        alertDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.dashboard_option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.wishlist->
            {
                startActivity(Intent(applicationContext,WishlistActivity::class.java))
            }
            R.id.cart->
            {
                startActivity(Intent(applicationContext,CartActivity::class.java))
            }

            R.id.logout->
            {
                sharedPreferences.edit().clear().commit()
                startActivity(Intent(applicationContext,LoginActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}