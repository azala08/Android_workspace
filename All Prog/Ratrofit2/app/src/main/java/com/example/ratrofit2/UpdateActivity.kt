package com.example.ratrofit2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ratrofit2.databinding.ActivityUpdateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity()
{
    lateinit var binding:ActivityUpdateBinding
    lateinit var apiinterface: Apiinterface
    var id=0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)
        var i = intent
        var pname = i.getStringExtra("pname")
        var pprice = i.getStringExtra("pprice")
        var pdes = i.getStringExtra("pdes")
        id = i.getIntExtra("id",404)

        binding.edt1.setText(pname)
        binding.edt2.setText(pprice)
        binding.edt3.setText(pdes)

        binding.btn1.setOnClickListener {

            var pname = binding.edt1.text.toString()
            var pprice = binding.edt2.text.toString()
            var pdes = binding.edt3.text.toString()

            var call: Call<Void> = apiinterface.updatedata(id,pname,pprice,pdes)
            call.enqueue(object: Callback<Void>
            {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                    Toast.makeText(applicationContext, "Updated", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                    Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()

                }
            })
        }
    }
}