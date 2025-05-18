package com.example.mymeditation.interfaces



import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymeditation.model.LikedSleepEntity

@Dao
interface LikedSleepDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(music: LikedSleepEntity)

    @Delete
    suspend fun delete(music: LikedSleepEntity)

    @Query("SELECT * FROM liked_sleep_music")
    fun getAll(): LiveData<List<LikedSleepEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM liked_sleep_music WHERE audioResId = :id)")
    suspend fun isLiked(id: Int): Boolean
}
