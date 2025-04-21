package com.example.meditationsleep

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SleepAdapter(
    private val items: List<SleepItem>,
    private val onItemClick: (SleepItem) -> Unit
) : RecyclerView.Adapter<SleepAdapter.SleepViewHolder>() {

    inner class SleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.ivImage)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val duration: TextView = itemView.findViewById(R.id.tvDuration)
        val category: TextView = itemView.findViewById(R.id.tvCategory)

        fun bind(item: SleepItem) {
            image.setImageResource(item.imageResId)
            title.text = item.title
            duration.text = item.duration
            category.text = item.category
            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sleep, parent, false)
        return SleepViewHolder(view)
    }

    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
