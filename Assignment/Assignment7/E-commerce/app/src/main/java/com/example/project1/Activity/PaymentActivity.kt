package com.example.project1.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1.ApiConfig.Apiinterface
import com.razorpay.PaymentResultListener
import com.example.project1.R
import com.example.project1.databinding.ActivityPaymentBinding
import com.razorpay.Checkout
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity : AppCompatActivity(), PaymentResultListener
{
    lateinit var binding: ActivityPaymentBinding
    lateinit var sharedPreferences: SharedPreferences
    var mob=""
    var pName=""
    var pPrice=""
    var pDesc=""
    var pImage=""
    var id =0
    lateinit var apiinterface: Apiinterface
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        val view = binding.root
        //sharedPreferences = getSharedPreferences("Session", Context.MODE_PRIVATE)
        binding.toolBar.title = "Payment"
        setSupportActionBar(binding.toolBar)
        binding.toolBar.setNavigationOnClickListener {
            super.finish()
        }
        setContentView(view)

        val intent = intent
        id=intent.getIntExtra("id",404)
        pName = intent.getStringExtra("pName").toString()
        pPrice = intent.getStringExtra("pPrice").toString()
        pDesc = intent.getStringExtra("pDesc").toString()
        pImage = intent.getStringExtra("pImage").toString()
        sharedPreferences = getSharedPreferences("Appproject", Context.MODE_PRIVATE)
        Toast.makeText(applicationContext,"Welcome "+sharedPreferences.getString("mob",""), Toast.LENGTH_LONG).show()

        mob = sharedPreferences.getString("n1","").toString()

        Picasso.get().load(pImage).into(binding.imageView)
        binding.tvName.text = pName
        binding.tvPrice.text = pPrice
        binding.tvDesc.text = pDesc

        binding.paymentBtn.setOnClickListener {

            val price = Integer.parseInt(pPrice!!) * 100

            val checkout = Checkout()
            checkout.setKeyID("rzp_test_7PX7ejznhsbYwJ")
            val obj = JSONObject()
            try {
                obj.put("name", pName)
                obj.put("description", "Test Payment")
                obj.put("theme.color", "")
                obj.put("currency", "INR")
                obj.put("amount", price)
                obj.put("prefill.contact", "8849817263")
                obj.put("prefill.email", "submitdata123@gmail.com")

                checkout.open(this, obj)
            } catch (e: JSONException) {
                e.printStackTrace()
            }


        }

    }

    override fun onPaymentSuccess(p0: String?)
    {

        Toast.makeText(applicationContext, "Payment Success", Toast.LENGTH_LONG).show()
        var i = Intent(applicationContext,TestActivity::class.java)
        i.putExtra("id",id)
        i.putExtra("pName",pName)
        i.putExtra("pPrice",pPrice)
        i.putExtra("pDesc",pDesc)
        i.putExtra("pImage",pImage)
        startActivity(i)



        //        Log.d("TOPSTOPS","Success")
//
//        var call:Call<Void> = apiinterface.paymentadd("a","1","a","https:\\/\\/prakrutitech.buzz\\/AndroidAPI\\/images\\/samsung.png","9275003605")
//
//        Log.d("TOPSTOPS","Success2")
////        call.enqueue(object:Callback<Void>
////        {
////            override fun onResponse(call: Call<Void>, response: Response<Void>) {
////
////               Log.d("TOPSTOPS1",response.toString())
////
////
////            }
////
////           override fun onFailure(call: Call<Void>, t: Throwable) {
////
////               Log.d("TOPSTOPS2",t.message.toString())
////            }
////        })
    }

    override fun onPaymentError(p0: Int, p1: String?)
    {
        //Toast.makeText(this, "Payment Failed :-  $p0 : OPSp1", Toast.LENGTH_SHORT).show()

        Log.d("TOPSTOPS","Fail")
    }

}