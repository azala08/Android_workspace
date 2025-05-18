package com.example.mymeditation

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment

class SetStatusBar {

    companion object {

        fun fromActivity(activity: Activity, isLightText: Boolean) {
            setStatusBarColor(activity, isLightText)
        }

        fun fromFragment(fragment: Fragment, isLightText: Boolean) {
            fragment.activity?.let { activity ->
                // Ensure window is not null and view is attached
                if (fragment.isAdded && fragment.view != null) {
                    setStatusBarColor(activity, isLightText)
                }
            }
        }

        private fun setStatusBarColor(activity: Activity, isLightText: Boolean) {
            val window = activity.window

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                var flags = window.decorView.systemUiVisibility

                // Add fullscreen layout flags
                flags = flags or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

                // Toggle light status bar text
                flags = if (isLightText) {
                    flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() // Light text (dark background)
                } else {
                    flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // Dark text (light background)
                }

                window.decorView.systemUiVisibility = flags
            }
        }
    }
}
