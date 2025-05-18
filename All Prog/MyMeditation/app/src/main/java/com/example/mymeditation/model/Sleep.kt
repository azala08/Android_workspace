package com.example.mymeditation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sleep(

    val imageRes: Int,            // For RecyclerView item
    val title: String,
    val min: String,
    val playOptionImageRes: Int, // For PlayOptionActivity image
    val audioResId: Int
) : Parcelable
