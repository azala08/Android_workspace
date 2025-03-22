package com.example.ratrofit_image

import android.Manifest.permission.READ_MEDIA_IMAGES
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ratrofit_image.databinding.ActivityMainBinding
import net.gotev.uploadservice.MultipartUploadRequest

class MainActivity : AppCompatActivity()
{
    lateinit var binding: ActivityMainBinding
    lateinit var filepath: Uri
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        permissions()

        binding.img.setOnClickListener {

            var i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i,1)



        }

        binding.btn1.setOnClickListener {



            filepath?.let { uri ->
                val filePath = getpath(uri)
                if (filePath != null)
                {
                    uploadImage(filePath)
                }
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null)
        {
            filepath= data.data!!
            binding.img.setImageURI(filepath)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun permissions()
    {
        if(ContextCompat.checkSelfPermission(this, READ_MEDIA_IMAGES)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(READ_MEDIA_IMAGES), 100)
        }
        else
        {
            Toast.makeText(applicationContext, "Permission Already Granted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadImage(filePath: String)
    {
        var pname = binding.edtpname.text.toString()
        var pprice = binding.edtpprice.text.toString()
        var pdes = binding.edtpdes.text.toString()
        var pstatus = binding.edtpstatus.text.toString()

        try
        {
            var request = MultipartUploadRequest(this,"https://prakrutitech.buzz/Rahul/insert.php")
                .addFileToUpload(filePath, "pimage")
                .addParameter("pname", pname)
                .addParameter("pprice", pprice)
                .addParameter("pdes", pdes)
                .addParameter("pstatus",pstatus)
                .setMaxRetries(2)
            request.startUpload()
            Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
        }
        catch(e:Exception)
        {
            e.printStackTrace()
        }

    }

    private fun getpath(uri: Uri): String?
    {
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
            if (columnIndex != -1) {
                return it.getString(columnIndex)
            }
        }
        return null
    }
}
