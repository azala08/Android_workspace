package com.example.project1.Activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1.Admin.Model.data2
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.R
import com.example.project1.databinding.ActivityAddSubProdcutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream

class AddSubProdcutActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var image: ImageView
    lateinit var btnsubmit: Button
    lateinit var imageuri: Uri
    lateinit var edt1: EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    var categoryNames = arrayOf("Select Category")
    var categoryid = arrayOf(0)
    var finalcid=0
    lateinit var apiinterface: Apiinterface
    lateinit var list: MutableList<data2>
    private lateinit var binding: ActivityAddSubProdcutBinding

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        imageuri = it!!
        image.setImageURI(it)
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubProdcutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
        apiinterface = ApiClient().getconnect().create(Apiinterface::class.java)
        list = ArrayList()

        var call: Call<List<data2>> = apiinterface.dashboard_viewdata2()
        call.enqueue(object : Callback<List<data2>> {
            override fun onResponse(call: Call<List<data2>>, response: Response<List<data2>>) {
                // list = response.body() as MutableList<DashboardModel>
                if (response.isSuccessful) {
                    list = response.body() as MutableList<data2>

                    // Extract the category names
                    categoryNames = list.map { it.name }.toTypedArray()
                    categoryid = list.map { it.id }.toTypedArray()
                    //cid = list.map { it.id }.toTypedArray()
                    // cid2=cid.get(0)
                    //var adapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,categoryNames)


                    // Set up the Spinner
                    val adapter = ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_spinner_item,
                        categoryNames
                    )
                    val adapter2 = ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_spinner_item,
                        categoryid
                    )

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    binding.spin.adapter = adapter
                    binding.spin2.adapter = adapter2
                }

            }

            override fun onFailure(call: Call<List<data2>>, t: Throwable) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_LONG).show()
            }
        })
        binding.spin.setOnItemSelectedListener(this)
        binding.spin2.setOnItemSelectedListener(this)
    }

    private fun setup()
    {
        edt1 = findViewById(R.id.edtname)
        edt2 = findViewById(R.id.edtprice)
        edt3 = findViewById(R.id.edtdes)
        image = findViewById(R.id.img)
        image.setOnClickListener { contract.launch("image/*") }
        btnsubmit = findViewById(R.id.btn1)
        btnsubmit.setOnClickListener { upload() }
    }

    private fun upload()
    {
        val filesDir = applicationContext.filesDir
        val file = File(filesDir,"image.png")
        val inputstream = contentResolver.openInputStream(imageuri)
        val outputstream = FileOutputStream(file)
        inputstream!!.copyTo(outputstream)

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("url",file.name,requestBody)
        val name1:RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt1.text.toString())
        val price1: RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt2.text.toString())
        val des1:RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt3.text.toString())
        val fcid:RequestBody = RequestBody.Companion.create(MultipartBody.FORM,finalcid.toString())


        val retrofit = Retrofit.Builder().baseUrl("https://prakrutitech.buzz/AndroidAPI/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)


        CoroutineScope(Dispatchers.IO).launch {

            val response = retrofit.uploadImage2(part,name1,price1,des1,fcid)
            Log.d("Cheezycoder",response.toString())

        }





    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var pos = position
        binding.spin2.setSelection(pos)

        if (binding.spin2 != null)
        {

            binding.spin2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        applicationContext,
                        "" + categoryid[position],
                        Toast.LENGTH_SHORT


                    ).show()

                    finalcid = categoryid[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}