package com.example.mymeditation

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.mymeditation.databinding.ActivityMainBinding
import com.example.mymeditation.activity.SignInActivity
import com.example.mymeditation.activity.SignUpActivity
import com.example.mymeditation.activity.WelcomeActivity

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate((layoutInflater))
        val view = binding.root
        setContentView(view)

        SetStatusBar.fromActivity(this,false)

        binding.btnSignup.setOnClickListener {
            startActivity(Intent(applicationContext, SignUpActivity::class.java))
        }

        binding.tvLoginLink.setOnClickListener {
            startActivity(Intent(applicationContext,SignInActivity::class.java))
        }
        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)

        if (isLoggedIn) {
            startActivity(Intent(this, WelcomeActivity::class.java))
        }

    }


    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed()
    {
        super.onBackPressed()
        finishAffinity()
    }
}




