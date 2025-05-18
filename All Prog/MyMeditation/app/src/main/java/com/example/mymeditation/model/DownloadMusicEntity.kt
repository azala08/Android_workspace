package com.example.mymeditation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloads")
data class DownloadMusicEntity(
    @PrimaryKey val audioResId: Int,
    val title: String,
    val imageResId: Int
)
