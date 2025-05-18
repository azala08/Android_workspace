package com.example.mymeditation

import android.app.Application
import androidx.lifecycle.*
import com.example.mymeditation.database.AppDatabase
import com.example.mymeditation.model.LikedMusicEntity
import com.example.mymeditation.model.MusicItem


class LikedMusicViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).likedMusicDao()

    val likedMusic: LiveData<List<LikedMusicEntity>> = dao.getAll()

    suspend fun isLiked(audioResId: Int): Boolean {
        return dao.isLikedRaw(audioResId) > 0
    }


    suspend fun like(music: MusicItem) {
        val entity = LikedMusicEntity(music.audioResId, music.title)
        dao.insert(entity)
    }

    suspend fun unlike(music: MusicItem) {
        val entity = LikedMusicEntity(music.audioResId, music.title)
        dao.delete(entity)
    }

    suspend fun unlike(entity: LikedMusicEntity) {
        dao.delete(entity)
    }
}
