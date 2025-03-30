package com.example.module6_4

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.module6_4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FAB click listener
        binding.fab.setOnClickListener {
            Snackbar.make(binding.root, "FAB Clicked!", Snackbar.LENGTH_SHORT).show()
        }

        // Button to navigate to SecondActivity
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left)
        }

        // Applying animation to FAB
        val fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.fab.startAnimation(fadeInAnim)
    }
}
