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
import com.example.mymeditation.model.LikedSleepEntity

class SleepLikedAdapter(
    private val onCancelClick: (LikedSleepEntity) -> Unit
) : ListAdapter<LikedSleepEntity, SleepLikedAdapter.SleepMusicViewHolder>(DIFF_CALLBACK) {

    private var onItemClick: ((LikedSleepEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (LikedSleepEntity) -> Unit) {
        onItemClick = listener
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LikedSleepEntity>() {
            override fun areItemsTheSame(oldItem: LikedSleepEntity, newItem: LikedSleepEntity) =
                oldItem.audioResId == newItem.audioResId

            override fun areContentsTheSame(oldItem: LikedSleepEntity, newItem: LikedSleepEntity) =
                oldItem == newItem
        }
    }

    inner class SleepMusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.tvFavouriteMusictitle)
        private val cancelIcon: ImageView = view.findViewById(R.id.imgFavouriteCancle)

        fun bind(item: LikedSleepEntity) {
            title.text = item.title
            title.setTextColor(itemView.context.getColor(android.R.color.white))
            cancelIcon.setOnClickListener {
                onCancelClick(item)
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepMusicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favourite_design, parent, false)
        return SleepMusicViewHolder(view)
    }


    override fun onBindViewHolder(holder: SleepMusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}