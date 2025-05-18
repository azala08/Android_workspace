package com.example.mymeditation.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymeditation.R
import com.example.mymeditation.databinding.ActivityCourseDeatilBinding
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.adapter.CourseDetailPageAdapter
import com.example.mymeditation.fragment.MaleFragment
import com.example.mymymeditation.fragment.FemaleFragment
import java.text.NumberFormat
import java.util.Calendar
import java.util.Locale

class CourseDetailActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityCourseDeatilBinding
    private lateinit var viewPagerAdapter: CourseDetailPageAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDeatilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        SetStatusBar.fromActivity(this,false)

        applyWindowInsets(binding.courseDetailMain)
        setupBackButton()
        setupViewPager()
        setupLikeButton()
        setupGreeting()

        binding.imgCourseDeatailDownload.setOnClickListener {
            Toast.makeText(applicationContext,"Downloded!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBackButton() {
        binding.imgCourseBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun setupViewPager() {
        viewPagerAdapter = CourseDetailPageAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(MaleFragment(), "MALE VOICE")
        viewPagerAdapter.addFragment(FemaleFragment(), "FEMALE VOICE")

        binding.vpCoureDetail.adapter = viewPagerAdapter
        binding.tlCourseDetail.setupWithViewPager(binding.vpCoureDetail)
    }

    @SuppressLint("SetTextI18n")
    private fun setupLikeButton() {
        var isLiked = false
        var likeCount = 24346 // Initial Like Count

        binding.imgLike.setOnClickListener {
            isLiked = !isLiked
            likeCount += if (isLiked) 1 else -1

            binding.imgLike.setImageResource(
                if (isLiked) R.drawable.heart_filled else R.drawable.select
            )

            val formattedCount = NumberFormat.getNumberInstance(Locale.US).format(likeCount)
            binding.tvLikeCount.text = "$formattedCount favorites"
        }
    }

    private fun setupGreeting() {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when (hour) {
            in 5..11 -> "Happy Morning"
            in 12..16 -> "Enjoy your afternoon!"
            in 17..20 -> "Have a lovely evening!"
            else -> "Good Night"
        }
        binding.tvHappyMorning.text = greeting
    }
    private fun applyWindowInsets(rootView: View) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding (
                systemBarsInsets.left,
                systemBarsInsets.top,
                systemBarsInsets.right,
                systemBarsInsets.bottom
            )
            insets
        }
    }
    @Deprecated("Use OnBackPressedDispatcher instead")
    override fun onBackPressed() {
        super.onBackPressed()
//        MediaPlayerManager.stopMusic()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}

