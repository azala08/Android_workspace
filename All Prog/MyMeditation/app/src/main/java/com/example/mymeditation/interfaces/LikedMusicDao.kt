package com.example.mymeditation.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymeditation.model.LikedMusicEntity


@Dao
interface LikedMusicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(music: LikedMusicEntity)

    @Delete
    suspend fun delete(music: LikedMusicEntity)

    @Query("SELECT * FROM liked_music")
    fun getAll(): LiveData<List<LikedMusicEntity>>

    @Query("SELECT COUNT(*) FROM liked_music WHERE audioResId = :id")
    suspend fun isLikedRaw(id: Int): Long

}


