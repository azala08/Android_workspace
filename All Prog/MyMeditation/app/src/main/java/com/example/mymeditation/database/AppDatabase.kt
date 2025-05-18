package com.example.mymeditation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymeditation.interfaces.LikedMusicDao
import com.example.mymeditation.model.LikedMusicEntity



@Database(entities = [LikedMusicEntity::class], version = 2) // Increased version from 1 to 2
abstract class AppDatabase : RoomDatabase() {
    abstract fun likedMusicDao(): LikedMusicDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mymeditation_db"
                )
                    .fallbackToDestructiveMigration() // Wipes data if schema changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
