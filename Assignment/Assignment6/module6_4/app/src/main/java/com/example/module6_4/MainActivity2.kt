package com.example.module6_4

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.module6_4.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity()
{
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Applying fade-in animation
        val fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.textView.startAnimation(fadeInAnim)

        // Back button click listener
        binding.btnBack.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out)
        }
    }
}