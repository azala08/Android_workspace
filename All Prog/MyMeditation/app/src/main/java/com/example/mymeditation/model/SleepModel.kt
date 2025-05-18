package com.example.mymeditation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SleepModel(
    val id: Int,
    val title: String,
    val audioResId: Int
) : Parcelable
