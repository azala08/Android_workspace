package com.example.task27

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity()
{
    lateinit var searchView: SearchView
    lateinit var btn :Button
    lateinit var recyclerView: RecyclerView
    lateinit var apiinterface: Apiinterface
    lateinit var itemlist : MutableList<ProductModel>
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.search)
        btn = findViewById(R.id.btninsert)
        recyclerView = findViewById(R.id.recycler)
        itemlist = ArrayList<ProductModel>()

        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)

        val call : Call<List<ProductModel>> = apiinterface.ViewProduct()
        call.enqueue(object : Callback<List<ProductModel>>
        {
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
                if (response.isSuccessful)
                {
                    itemlist = response.body() as MutableList<ProductModel>
                    adapter = ProductAdapter(applicationContext, itemlist)
                    recyclerView.adapter = adapter
                }
                else
                {
                    Toast.makeText(applicationContext, "Failed to load products", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
            }

        })

        btn.setOnClickListener {
            startActivity(Intent(applicationContext,InsertActivity::class.java))
            finish()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter1(newText)
                return false
            }

        })

    }

    private fun filter1(newText: String?) {
        val filteredlist: ArrayList<ProductModel> = ArrayList()
        for (item in itemlist) {
            if (newText != null) {
                if (item.pname.toLowerCase().contains(newText.toLowerCase())) {
                    filteredlist.add(item)
                }
            }
        }
        if (filteredlist.isEmpty())
        {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        }
        else{
            adapter.filterList((filteredlist))
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed()
    {
        finishAffinity()
        super.onBackPressed()
    }


}