package com.example.mymeditation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymeditation.interfaces.MusicDao
import com.example.mymeditation.model.DownloadMusicEntity
import com.example.mymeditation.model.LikedMusicEntity

@Database(entities = [DownloadMusicEntity::class, LikedMusicEntity::class], version = 1)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao

    companion object {
        @Volatile private var INSTANCE: MusicDatabase? = null

        fun getInstance(context: Context): MusicDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MusicDatabase::class.java,
                    "music_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
