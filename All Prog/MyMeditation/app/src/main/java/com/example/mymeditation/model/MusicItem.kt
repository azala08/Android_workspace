package com.example.mymeditation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicItem(
    val id: String,
    val title: String,
    val duration: String,
    val isPlaying: Boolean = false,
    val imageResId: Int,
    val audioUrl: String = " ",
    val audioResId: Int
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        return other is MusicItem && title == other.title && audioResId == other.audioResId
    }

    override fun hashCode(): Int {
        return 31 * title.hashCode() + audioResId
    }
}