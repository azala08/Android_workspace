package com.example.mymeditation.activity


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.example.mymeditation.R
import com.example.mymeditation.databinding.ActivityHomeBinding
import com.example.mymeditation.fragment.*
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.fragment.MusicFragment
import com.example.mymeditation.fragment.SleepFragment
import com.example.mymeditation.fragment.UserFragment
import com.example.mymymeditation.fragment.MeditateFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SetStatusBar.fromActivity(this, false)

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        applyWindowInsets(binding.mainHome)

        setupUserName()

        handleIntentFragment()

        setupNavigation()
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

    private fun setupUserName() {
        val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("name", "Hi") // Default fallback
        binding.tvUserNav.text = "$userName "
    }

    private fun handleIntentFragment() {
        val openFragment = intent.getStringExtra("openFragment")
        when (openFragment) {
            "sleep" -> selectTab(binding.cvSleepNav, SleepFragment(), binding.lytSleepNav, "sleep")
            else -> selectTab(binding.cvHomeNav, HomeFragment(), binding.lytHomeNav, "home")
        }
    }

    private fun setupNavigation() {
        binding.lytHomeNav.setOnClickListener {
            selectTab(binding.cvHomeNav, HomeFragment(), binding.lytHomeNav, "home")
        }
        binding.lytSleepNav.setOnClickListener {
            startActivity(Intent(this, WelcomeSleepActivity::class.java))
        }
        binding.lytMeditateNav.setOnClickListener {
            selectTab(binding.cvMeditateNav, MeditateFragment(), binding.lytMeditateNav, "meditate")
        }
        binding.lytMusiceNav.setOnClickListener {
            selectTab(binding.cvMusicNav, MusicFragment(), binding.lytMusiceNav, "music")
        }
        binding.lytUserNav.setOnClickListener {
            selectTab(binding.cvUserNav, UserFragment(), binding.lytUserNav, "user")
        }
    }

    private fun selectTab(
        selectedCardView: CardView,
        selectedFragment: Fragment,
        selectedLayout: LinearLayout,
        tabKey: String
    ) {
        resetAllNavCardColors()
        selectedCardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.nav_card))

        setFragment(selectedFragment)
        setSelectedTab(selectedLayout)
        updateNavIcons(tabKey)
        updateNavTextColors(tabKey)
        updateNavBackground(tabKey)
      
    }


    private fun resetAllNavCardColors() {
        val defaultColor = ContextCompat.getColor(this, android.R.color.transparent)
        listOf(
            binding.cvHomeNav,
            binding.cvSleepNav,
            binding.cvMeditateNav,
            binding.cvMusicNav,
            binding.cvUserNav
        ).forEach { it.setCardBackgroundColor(defaultColor) }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    private fun setSelectedTab(selectedLayout: LinearLayout) {
        listOf(
            binding.lytHomeNav,
            binding.lytSleepNav,
            binding.lytMeditateNav,
            binding.lytMusiceNav,
            binding.lytUserNav
        ).forEach { it.isSelected = it == selectedLayout }
    }

    private fun updateNavIcons(selected: String) {
        binding.imgNavHome.setImageResource(if (selected == "home") R.drawable.home_white else R.drawable.home)
        binding.imgNavSleep.setImageResource(if (selected == "sleep") R.drawable.sleep_white else R.drawable.sleep)
        binding.imgNavMeditate.setImageResource(if (selected == "meditate") R.drawable.meditate_white else R.drawable.meditate)
        binding.imgNavMusic.setImageResource(if (selected == "music") R.drawable.music_white else R.drawable.music_menu)
        binding.imgNavUser.setImageResource(if (selected == "user") R.drawable.user_white else R.drawable.user)
    }

    private fun updateNavTextColors(selected: String) {
        val selectedColor = ContextCompat.getColor(this, R.color.purple)
        val selectedColorLight = ContextCompat.getColor(this, R.color.white)
        val defaultColor = ContextCompat.getColor(this, R.color.grey)

        binding.tvHomeNav.setTextColor(if (selected == "home") selectedColor else defaultColor)
        binding.tvSleepNav.setTextColor(if (selected == "sleep") selectedColorLight else defaultColor)
        binding.tvMeditateNav.setTextColor(if (selected == "meditate") selectedColor else defaultColor)
        binding.tvMusicNav.setTextColor(if (selected == "music") selectedColorLight else defaultColor)
        binding.tvUserNav.setTextColor(if (selected == "user") selectedColorLight else defaultColor)
    }

    private fun updateNavBackground(tabKey: String) {
        val colorRes = when (tabKey) {
            "sleep", "music" -> R.color.sleep_nav
            "user" -> R.color.sleep_nav
            else -> android.R.color.white
        }
        binding.lytNav.setBackgroundColor(ContextCompat.getColor(this, colorRes))
    }

    @Deprecated("Use OnBackPressedDispatcher instead")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext,ChooseTopicActivity::class.java))
        finish()
    }
}
