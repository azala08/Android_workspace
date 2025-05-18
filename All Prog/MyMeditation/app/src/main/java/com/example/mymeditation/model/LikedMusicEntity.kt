package com.example.mymeditation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_music")
data class LikedMusicEntity(
    @PrimaryKey val audioResId: Int,
    val title: String

)
