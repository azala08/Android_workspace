package com.example.meditationsleep

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SleepItem(
    val title: String,
    val duration: String,
    val category: String,
    val imageResId: Int,
    val description: String = "",
    val favorites: String = "",
    val listening: String = ""
) : Parcelable
