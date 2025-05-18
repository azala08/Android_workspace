package com.example.mymeditation.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mymeditation.Utils.getImageFoldersWithPaths
import com.example.mymeditation.adapter.CourseDetailPageAdapter
import com.example.mymeditation.databinding.ActivityCustomGalleryBinding
import com.example.mymeditation.fragment.ImageGridFragment

class CustomGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        addListeners()
        checkPermissionAndLoadImages()
    }

    private fun checkPermissionAndLoadImages() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                    100
                )
            } else {
                setUpViewPager()
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    100
                )
            } else {
                setUpViewPager()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setUpViewPager()
        }
    }

    private fun init() {
        binding.root.setOnApplyWindowInsetsListener { _, insets ->
            val statusBarHeight = insets.systemWindowInsetTop
            val layoutParams = binding.toolbar.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = statusBarHeight
            binding.toolbar.layoutParams = layoutParams
            insets
        }

    }

    private fun addListeners() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpViewPager() {
        val folders = getImageFoldersWithPaths(this)
        val adapter = CourseDetailPageAdapter(supportFragmentManager)
        for ((folderName, folderPath) in folders) {
            val fragment = ImageGridFragment.newInstance(folderPath)
            adapter.addFragment(fragment, folderName)
        }
        binding.viewPagerGallery.adapter = adapter
        binding.tabLayoutGallery.setupWithViewPager(binding.viewPagerGallery)
    }
}
