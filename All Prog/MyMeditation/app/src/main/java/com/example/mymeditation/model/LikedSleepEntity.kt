package com.example.mymeditation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "liked_sleep_music")

data class LikedSleepEntity(
    @PrimaryKey val audioResId: Int,
    val title: String
): Parcelable
