package com.example.mymeditation


import android.app.Application
import androidx.lifecycle.*
import com.example.mymeditation.database.SleepDatabase
import com.example.mymeditation.model.LikedSleepEntity
import com.example.mymeditation.model.MusicItem
import com.example.mymeditation.model.Sleep


class LikedSleepViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = SleepDatabase.getDatabase(application).likedSleepDao()

    val likedMusic: LiveData<List<LikedSleepEntity>> = dao.getAll()

    suspend fun isLiked(audioResId: Int): Boolean = dao.isLiked(audioResId)

    suspend fun like(music: Sleep) {
        val entity = LikedSleepEntity(music.audioResId, music.title)
        dao.insert(entity)
    }

    suspend fun unlike(music: Sleep) {
        val entity = LikedSleepEntity(music.audioResId, music.title)
        dao.delete(entity)
    }

    suspend fun unlike(entity: LikedSleepEntity) {
        dao.delete(entity)
    }
}
