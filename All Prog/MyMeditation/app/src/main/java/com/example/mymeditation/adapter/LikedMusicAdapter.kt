package com.example.mymeditation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.R
import com.example.mymeditation.model.LikedMusicEntity

class LikedMusicAdapter(
    private val onCancelClick: (LikedMusicEntity) -> Unit
) : ListAdapter<LikedMusicEntity, LikedMusicAdapter.MusicViewHolder>(DIFF_CALLBACK) {

    private var onItemClick: ((LikedMusicEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (LikedMusicEntity) -> Unit) {
        onItemClick = listener
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LikedMusicEntity>() {
            override fun areItemsTheSame(oldItem: LikedMusicEntity, newItem: LikedMusicEntity) =
                oldItem.audioResId == newItem.audioResId

            override fun areContentsTheSame(oldItem: LikedMusicEntity, newItem: LikedMusicEntity) =
                oldItem == newItem
        }
    }

    inner class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.tvFavouriteMusictitle)
        private val cancelIcon: ImageView = view.findViewById(R.id.imgFavouriteCancle)

        fun bind(item: LikedMusicEntity) {
            title.text = item.title
            cancelIcon.setOnClickListener {
                onCancelClick(item)
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favourite_design, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
