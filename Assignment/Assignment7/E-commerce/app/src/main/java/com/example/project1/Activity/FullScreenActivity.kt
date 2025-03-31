package com.example.project1.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.R
import com.example.project1.databinding.ActivityFullScreenBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FullScreenActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityFullScreenBinding
    lateinit var apiinterface: Apiinterface
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent
        val image = intent.getStringExtra("image")
        val giftName = intent.getStringExtra("name")
        val giftDesc = intent.getStringExtra("desc")
        val giftPrice = intent.getStringExtra("price")
        apiinterface = ApiClient().getconnect().create(Apiinterface::class.java)
        sharedPreferences = getSharedPreferences("Appproject", Context.MODE_PRIVATE)
        var data = sharedPreferences.getString("n1", "")
        Picasso.get().load(image).into(binding.imageView)
        binding.giftName.text = giftName
        binding.giftDesc.text = giftDesc
        binding.giftPrice.text = giftPrice

        binding.mToolbar.setNavigationOnClickListener {
            super.finish()


        }
        binding.wishListButton.setOnClickListener {

            var call: Call<Void> = apiinterface.addwishlist(giftName,giftDesc,giftPrice,image,data)
            call.enqueue(object :Callback<Void>
            {
                override fun onResponse(call: Call<Void>, response: Response<Void>)
                {
                    Toast.makeText(applicationContext, "Added to Wishlist", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,WishlistActivity::class.java))
                }

                override fun onFailure(call: Call<Void>, t: Throwable)
                {
                    Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
                }
            })

        }

    }
}
