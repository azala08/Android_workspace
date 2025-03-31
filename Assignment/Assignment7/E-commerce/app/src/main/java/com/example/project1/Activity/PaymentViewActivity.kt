package com.example.project1.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Adapter.PaymentAdapter
import com.example.project1.Admin.Model.PaymentModel
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.Model.WishlistModel
import com.example.project1.R
import com.example.project1.databinding.ActivityAddProductBinding
import com.example.project1.databinding.ActivityPaymentViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentViewActivity :AppCompatActivity()
{
    private lateinit var binding: ActivityPaymentViewBinding
    lateinit var apiinterface: Apiinterface
    lateinit var paymentlist:MutableList<PaymentModel>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var layoutmanager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager=layoutmanager

        apiinterface = ApiClient().getconnect().create(Apiinterface::class.java)
        var call: Call<List<PaymentModel>> = apiinterface.paymentview()

        call.enqueue(object: Callback<List<PaymentModel>>
        {
            override fun onResponse(call: Call<List<PaymentModel>>, response: Response<List<PaymentModel>>)
            {
                paymentlist = response.body() as MutableList<PaymentModel>

                var Adapter = PaymentAdapter(applicationContext, paymentlist)
                binding.recycler.adapter = Adapter
            }

            override fun onFailure(call: Call<List<PaymentModel>>, t: Throwable)
            {
                Toast.makeText(applicationContext, "Payment Detail Not fetched", Toast.LENGTH_SHORT).show()
            }
        })
    }
}