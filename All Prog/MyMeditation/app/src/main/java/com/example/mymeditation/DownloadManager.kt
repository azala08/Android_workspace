package com.example.mymeditation

import android.content.Context
import android.net.Uri

object DownloadManager {
    private const val PREF_NAME = "downloads"

    fun isDownloaded(context: Context, audioResId: Int): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.contains(audioResId.toString())
    }

    fun getDownloadUri(context: Context, audioResId: Int): Uri? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(audioResId.toString(), null)?.let { Uri.parse(it) }
    }

    fun markAsDownloaded(context: Context, audioResId: Int, uri: Uri) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(audioResId.toString(), uri.toString()).apply()
    }

    fun removeDownload(context: Context, audioResId: Int) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(audioResId.toString()).apply()
    }

    fun getAllDownloads(context: Context): Map<String, *> {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).all
    }
}
