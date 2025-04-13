package com.example.meditationapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meditationapp.Model.Meditate
import com.example.meditationapp.R

class MeditateAdapter(private val categories: List<Meditate>) :
    RecyclerView.Adapter<MeditateAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.catrgory_item, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryImage.setImageResource(category.image)
        holder.categoryName.text = category.name
    }

    override fun getItemCount() = categories.size
}

class MeditationAdapter(private val meditations: List<Meditate>) :
    RecyclerView.Adapter<MeditationAdapter.MeditationViewHolder>() {

    inner class MeditationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val meditationImage: ImageView = itemView.findViewById(R.id.meditationImage)
        val meditationTitle: TextView = itemView.findViewById(R.id.meditationTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.meditation_item, parent, false)
        return MeditationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        val meditation = meditations[position]
        holder.meditationImage.setImageResource(meditation.image)
        holder.meditationTitle.text = meditation.title
    }

    override fun getItemCount() = meditations.size
}