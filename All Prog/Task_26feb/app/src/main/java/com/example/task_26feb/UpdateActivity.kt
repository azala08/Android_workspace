package com.example.task_26feb

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task_26feb.databinding.ActivityUpdateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity()
{
    lateinit var binding: ActivityUpdateBinding
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
        var pstatus = i.getStringExtra("pstatus")
        var pimage = i.getStringExtra("pimage")
        id = i.getIntExtra("id",404)

        binding.pname.setText(pname)
        binding.pprice.setText(pprice)
        binding.pdesc.setText(pdes)
        binding.pstatus.setText(pstatus)

        binding.btn2.setOnClickListener {

            var pname = binding.pname.text.toString()
            var pprice = binding.pprice.text.toString()
            var pdes = binding.pdesc.text.toString()
            var pstatus = binding.pstatus.text.toString()

            var call: Call<Void> = apiinterface.updatedata(id,pname,pprice,pdes,pstatus)
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