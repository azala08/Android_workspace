package com.example.module5_assignment

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class MyDbClass:RoomDatabase()
{
    abstract fun daoClass(): MyDao
}