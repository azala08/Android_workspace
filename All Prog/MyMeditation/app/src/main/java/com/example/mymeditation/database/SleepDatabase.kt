package com.example.mymeditation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymeditation.interfaces.LikedSleepDao
import com.example.mymeditation.model.LikedSleepEntity

@Database(entities = [LikedSleepEntity::class], version = 2) // Increased version from 1 to 2
abstract class SleepDatabase : RoomDatabase() {
    abstract fun likedSleepDao(): LikedSleepDao

    companion object {
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getDatabase(context: Context): SleepDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SleepDatabase::class.java,
                    "mymeditation_sleep_db"
                )
                    .fallbackToDestructiveMigration() // Wipes data if schema changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
