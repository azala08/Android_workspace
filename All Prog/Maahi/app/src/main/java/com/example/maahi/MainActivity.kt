package com.example.maahi

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var productsRecyclerView: RecyclerView
    private var cartCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.bannerViewPager)
        indicatorContainer = findViewById(R.id.indicatorContainer)
        productsRecyclerView = findViewById(R.id.productsRecyclerView)

        setupBannerSlider()
        setupProductsList()
        updateCartBadge()
    }
    private fun setupBannerSlider() {
        val bannerImages = listOf(
            R.drawable.maahi1,
            R.drawable.maahi2,
            R.drawable.maahi3,
            R.drawable.maahi4,
            R.drawable.maahi5,
            R.drawable.maahi6
            // Add more banner images
        )

        viewPager.adapter = ImageSliderAdapter(bannerImages)

        // Add indicators
        bannerImages.indices.forEach { i ->
            val indicator = View(this).apply {
                setBackgroundResource(if (i == 0) R.drawable.indicator_active else R.drawable.indicator_inactive)
                layoutParams = LinearLayout.LayoutParams(16, 16).apply {
                    marginStart = 8
                    marginEnd = 8
                }
            }
            indicatorContainer.addView(indicator)
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicators(position)
            }
        })
    }



    private fun updateIndicators(position: Int) {
        for (i in 0 until indicatorContainer.childCount) {
            indicatorContainer.getChildAt(i).setBackgroundResource(
                if (i == position) R.drawable.indicator_active else R.drawable.indicator_inactive
            )
        }
    }

    private fun setupProductsList() {
        val products = listOf(
            Product(1, "SHRIKHAND KESAR 100 GM", 35.0, R.drawable.shrikhand100gm),
            Product(2, "SHRIKHAND KESAR 500 GM", 140.0, R.drawable.shrikhand140gm),
            Product(3, "SHRIKHAND ELAICHI 100 GM", 30.0, R.drawable.shrikhandelaichi500gm),
            Product(4, "SHRIKHAND ELAICHI 500 GM", 130.0, R.drawable.cheesecube) ,
            Product(4, "SHRIKHAND ELAICHI 500 GM", 130.0, R.drawable.cheeseslice),
            Product(4, "SHRIKHAND ELAICHI 500 GM", 130.0, R.drawable.milk),
            Product(4, "SHRIKHAND ELAICHI 500 GM", 130.0, R.drawable.milkshake1),
            Product(4, "SHRIKHAND ELAICHI 500 GM", 130.0, R.drawable.milkshake2),
            Product(4, "SHRIKHAND ELAICHI 500 GM", 130.0, R.drawable.milkshake3),

        )

        productsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        productsRecyclerView.adapter = ProductAdapter(products) { position, quantity ->
            cartCount += quantity
            updateCartBadge()
        }
    }

    private fun updateCartBadge() {
        findViewById<TextView>(R.id.cartBadge).text = cartCount.toString()
    }
}