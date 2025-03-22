package com.example.module4_assignment3

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.module4_assignment3.ProfileFragment
import com.example.module4_assignment3.SettingFragment

class MainActivity : AppCompatActivity()
{
    lateinit var home : LinearLayout
    lateinit var profile : LinearLayout
    lateinit var setting : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home = findViewById(R.id.HomeLayout)
        profile = findViewById(R.id.ProfileLayout)
        setting = findViewById(R.id.SettingLayout)

        home.setOnClickListener {

            var homefragment = HomeFragment()
            var fragmentmanager = supportFragmentManager
            var transaction = fragmentmanager.beginTransaction()
            transaction.replace(R.id.frame,homefragment).commit()

        }

        profile.setOnClickListener {

            var profilefragment = ProfileFragment()
            var fragmentmanager = supportFragmentManager
            var transaction = fragmentmanager.beginTransaction()
            transaction.replace(R.id.frame,profilefragment).commit()

        }

        setting.setOnClickListener {

            var settingfragment = SettingFragment()
            var fragmentmanager = supportFragmentManager
            var transaction = fragmentmanager.beginTransaction()
            transaction.replace(R.id.frame,settingfragment).commit()

        }

        val homeFragment = HomeFragment()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.frame, homeFragment).commit()
    }
}