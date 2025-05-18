package com.example.mymeditation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.databinding.ItemDownloadedBinding
import com.example.mymeditation.model.DownloadMusicEntity

class DownloadAdapter(
    private val onMenuClicked: (DownloadMusicEntity, View) -> Unit
) : ListAdapter<DownloadMusicEntity, DownloadAdapter.DownloadedViewHolder>(Diff()) {

    class DownloadedViewHolder(val binding: ItemDownloadedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDownloadedBinding.inflate(inflater, parent, false)
        return DownloadedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DownloadedViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvTitle.text = item.title
        holder.binding.ivMusic.setImageResource(item.imageResId)
        holder.binding.btnMenu.setOnClickListener {
            onMenuClicked(item, it)
        }
    }

    class Diff : DiffUtil.ItemCallback<DownloadMusicEntity>() {
        override fun areItemsTheSame(oldItem: DownloadMusicEntity, newItem: DownloadMusicEntity) =
            oldItem.audioResId == newItem.audioResId

        override fun areContentsTheSame(oldItem: DownloadMusicEntity, newItem: DownloadMusicEntity) =
            oldItem == newItem
    }
}
