package com.example.mymeditation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.R
import java.io.File

class DownloadedMusicAdapter(
    private val context: Context,
    private val songs: List<File>,
    private val onFavoriteClick: (File) -> Unit,
    private val onRenameClick: (File) -> Unit,
    private val onDeleteClick: (File) -> Unit
) : RecyclerView.Adapter<DownloadedMusicAdapter.DownloadViewHolder>() {

    inner class DownloadViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val menu: ImageView = view.findViewById(R.id.btnMenu)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_downloaded, parent, false)
        return DownloadViewHolder(view)
    }

    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        val song = songs[position]
        holder.title.text = song.nameWithoutExtension

        holder.menu.setOnClickListener {
            val popup = PopupMenu(context, holder.menu)
            MenuInflater(context).inflate(R.menu.download_menu, popup.menu)
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_favorite -> onFavoriteClick(song)
                    R.id.menu_rename -> onRenameClick(song)
                    R.id.menu_delete -> onDeleteClick(song)
                }
                true
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int = songs.size
}
