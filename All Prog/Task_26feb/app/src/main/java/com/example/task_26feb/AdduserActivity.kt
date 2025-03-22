package com.example.task_26feb

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AdduserActivity : AppCompatActivity()
{
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var edt4:EditText
    lateinit var btn1:Button
    lateinit var btnimg :Button

    lateinit var ibtn:Button
    lateinit var imageview: ImageView
    lateinit var filepath: Uri
    lateinit var bitmap: Bitmap
    lateinit var apiinterface: Apiinterface

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adduser)


        edt1 = findViewById(R.id.pname)
        edt2 = findViewById(R.id.pprice)
        edt3 = findViewById(R.id.pdesc)
        edt4 = findViewById(R.id.pstatus)
        btn1 = findViewById(R.id.btn1)

        ibtn = findViewById(R.id.imgbtn)
        imageview = findViewById(R.id.itemimage)
        apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)

        permissions()





        btn1.setOnClickListener {

            var n = edt1.text.toString()
            var p = edt2.text.toString()
            var d = edt3.text.toString()
            var s = edt4.text.toString()
            var i: String = btnimg.text.toString()

            var call: Call<Void> = apiinterface.insertdata(n, p, d, s,i)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "Inserted", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                    Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
                }


            })
        }
    }

    private fun permissions() {

//        if (ContextCompat.checkSelfPermission(
//                this,
//                READ_MEDIA_IMAGES
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(this, arrayOf(READ_MEDIA_IMAGES), 100)
//        } else {
//            Toast.makeText(applicationContext, "Permission already granted", Toast.LENGTH_LONG)
//                .show()
//        }
        if (checkSelfPermission(READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE),100)
        }
        else{
            Toast.makeText(applicationContext, "Permission Alredy Granted", Toast.LENGTH_SHORT).show()
        }

    }





}