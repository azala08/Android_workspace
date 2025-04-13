package com.example.meditationapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meditationapp.Model.Song
import com.example.meditationapp.R

class SongAdapter(private val songs: List<Song>) :
    RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.songTitle)
        val duration: TextView = itemView.findViewById(R.id.songDuration)
        val playButton: ImageView = itemView.findViewById(R.id.playButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.title.text = song.title
        holder.duration.text = song.duration
        holder.playButton.setImageResource(
            if (song.isPlaying) R.drawable.pause else R.drawable.play
        )

        holder.playButton.setOnClickListener {
            // Handle play/pause
        }
    }

    override fun getItemCount() = songs.size
}
