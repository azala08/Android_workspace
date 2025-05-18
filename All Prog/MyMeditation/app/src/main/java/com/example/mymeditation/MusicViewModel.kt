package com.example.mymeditation

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.liveData
import com.example.mymeditation.database.MusicDatabase
import com.example.mymeditation.interfaces.MusicDao
import com.example.mymeditation.model.DownloadMusicEntity
import com.example.mymeditation.model.LikedMusicEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicViewModel(application: Application) : AndroidViewModel(application) {

    private val musicDao: MusicDao = MusicDatabase.getInstance(application).musicDao()

    val downloadedSongs: LiveData<List<DownloadMusicEntity>> =
        musicDao.getAllDownloaded().asLiveData()

    fun addDownloaded(song: DownloadMusicEntity) = viewModelScope.launch(Dispatchers.IO) {
        musicDao.insertDownloaded(song)
    }

    fun removeDownloaded(audioResId: Int) = viewModelScope.launch(Dispatchers.IO) {
        musicDao.removeDownloadedById(audioResId)
    }

    suspend fun isDownloaded(audioResId: Int): Boolean {
        return musicDao.isDownloaded(audioResId)
    }

    fun addFavorite(song: LikedMusicEntity) = viewModelScope.launch(Dispatchers.IO) {
        musicDao.insertFavorite(song)
    }

    fun removeFavorite(audioResId: Int) = viewModelScope.launch(Dispatchers.IO) {
        musicDao.removeFavoriteById(audioResId)
    }

    suspend fun isFavorite(audioResId: Int): Boolean {
        return musicDao.isFavorite(audioResId)
    }
}
