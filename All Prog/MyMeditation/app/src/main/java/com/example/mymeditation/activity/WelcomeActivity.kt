package com.example.mymeditation.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymeditation.R
import com.example.mymeditation.databinding.ActivityWelcomeBinding
import com.example.mymeditation.SetStatusBar

class WelcomeActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityWelcomeBinding

    @SuppressLint("MissingInflatedId", "CutPasteId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        applyWindowInsets(binding.mainWelcome)
        SetStatusBar.fromActivity(this,true)

        val userName = intent.getStringExtra("name") ?: getSharedPreferences("UserData", Context.MODE_PRIVATE)
            .getString("name", "User")

        val welcomeTextView = findViewById<TextView>(R.id.tvWelcomeUserName)
        welcomeTextView.text = "Hi $userName, Welcome"

        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(applicationContext,ChooseTopicActivity::class.java))
        }
    }
    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed()
    {
        super.onBackPressed()
        finishAffinity()
    }
    private fun applyWindowInsets(rootView: View) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBarsInsets.left,
                systemBarsInsets.top,
                systemBarsInsets.right,
                systemBarsInsets.bottom
            )
            insets
        }
    }
}
