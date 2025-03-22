package com.example.task27

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.task27.databinding.ActivityUpdateBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UpdateActivity : AppCompatActivity()
{
    lateinit var binding: ActivityUpdateBinding
    lateinit var apiinterface: Apiinterface
    var id=0
    private var filepath: Uri? = null
    lateinit var bitmap: Bitmap
     var apiInterface: Apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)

        var i = intent
        id = i.getIntExtra("pid",404)
        var pname = i.getStringExtra("pname")
        var pprice = i.getStringExtra("pprice")
        var pdes = i.getStringExtra("pdes")
        var pstatus = i.getStringExtra("pstatus")
        var pimage = i.getStringExtra("pimage")
        Toast.makeText(applicationContext, ""+id, Toast.LENGTH_SHORT).show()

        binding.pname.setText(pname)
        binding.pprice.setText(pprice)
        binding.pdesc.setText(pdes)
        binding.pstatus.setText(pstatus)

        if(!pimage.isNullOrEmpty())
        {
            Glide.with(this)
                .load(pimage)
                .placeholder(R.drawable.addimg)
                .into(binding.img1)
        }

        binding.img1.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 101)
        }

        binding.btn2.setOnClickListener {

            if (filepath != null) {
                val filePath = getpath(filepath!!)
                if (filePath != null) {
                    uploadImage(filePath)
                } else {
                    Toast.makeText(this, "Failed to get image path", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
            }

//            var pname = binding.pname.text.toString()
//            var pprice = binding.pprice.text.toString()
//            var pdes = binding.pdesc.text.toString()
//            var pstatus = binding.pstatus.text.toString()
//
//            val name: RequestBody = RequestBody.create(MultipartBody.FORM, pname)
//            val price: RequestBody = RequestBody.create(MultipartBody.FORM, pprice)
//            val desc: RequestBody = RequestBody.create(MultipartBody.FORM, pdes)
//            val status: RequestBody = RequestBody.create(MultipartBody.FORM, pstatus)
//
//            var call : Call<ResponseBody> = apiinterface.UpdateProduct(id,name,price,desc,status)
//
//            call.enqueue(object : Callback<ResponseBody> {
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    Toast.makeText(applicationContext, "Updated", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(applicationContext,MainActivity::class.java))
//                    finish()
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable)
//                {
//                    Toast.makeText(applicationContext, "Updated Fail", Toast.LENGTH_SHORT).show()
//
//                }
//            })
        }
    }

    private fun uploadImage(filePath: String)
    {
        var pname = binding.pname.text.toString()
        var pprice = binding.pprice.text.toString()
        var pdes = binding.pdesc.text.toString()
        var pstatus = binding.pstatus.text.toString()

        val name: RequestBody = RequestBody.create(MultipartBody.FORM, pname)
        val price: RequestBody = RequestBody.create(MultipartBody.FORM, pprice)
        val desc: RequestBody = RequestBody.create(MultipartBody.FORM, pdes)
        val status: RequestBody = RequestBody.create(MultipartBody.FORM, pstatus)

        val file = File(filePath)

        val requestFile = file.asRequestBody(contentResolver.getType(filepath!!)!!.toMediaTypeOrNull())

        val imgbody: MultipartBody.Part = MultipartBody.Part.createFormData("pimage", file.name, requestFile)

        val call: Call<ResponseBody> = apiInterface.UpdateProduct(id,name,price,desc,status,imgbody)

        call.enqueue(object : Callback<ResponseBody>
        {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Updated", Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Upload Failed: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(applicationContext, "Upload Failed Details", Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun getpath(uri: Uri): String? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        contentResolver.query(uri, filePathColumn, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return cursor.getString(columnIndex)
            }
        }
        return uri.path
    }


    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK && data != null) {
            filepath = data.data
            binding.img1.setImageURI(filepath)
        }
    }
}

