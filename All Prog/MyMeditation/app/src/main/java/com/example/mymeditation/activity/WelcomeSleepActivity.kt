package com.example.mymeditation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymeditation.databinding.ActivityWelcomeSleepBinding
import com.example.mymeditation.SetStatusBar


class WelcomeSleepActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityWelcomeSleepBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeSleepBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        applyWindowInsets(binding.mainSleepMain)
        SetStatusBar.fromActivity(this,true)


        binding.btnWelcomeSleep.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("openFragment", "sleep")
            startActivity(intent)
            finish()
        }

    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed()
    {
        val intent = Intent(applicationContext,HomeActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
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


