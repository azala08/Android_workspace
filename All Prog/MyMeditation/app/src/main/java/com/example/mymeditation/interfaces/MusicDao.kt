package com.example.mymeditation.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymeditation.model.DownloadMusicEntity
import com.example.mymeditation.model.LikedMusicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    // Download related
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloaded(entity: DownloadMusicEntity)

    @Query("SELECT * FROM downloads")
    fun getAllDownloaded(): Flow<List<DownloadMusicEntity>>

    @Query("DELETE FROM downloads WHERE audioResId = :id")
    suspend fun removeDownloadedById(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM downloads WHERE audioResId = :id)")
    suspend fun isDownloaded(id: Int): Boolean

    // Favorites related
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(entity: LikedMusicEntity)

    @Query("SELECT * FROM liked_music")
    suspend fun getAllFavorites(): List<LikedMusicEntity>

    @Query("DELETE FROM liked_music WHERE audioResId = :id")
    suspend fun removeFavoriteById(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM liked_music WHERE audioResId = :id)")
    suspend fun isFavorite(id: Int): Boolean
}