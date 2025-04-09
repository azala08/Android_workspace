package com.example.meditationapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TopicAdapter(private val topics: List<com.example.meditationapp.Topic>) :
    RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    inner class TopicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.topicTitle)
        val image: ImageView = view.findViewById(R.id.topicImage)
        val container: View = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.design, parent, false)
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = topics[position]
        holder.title.text = topic.title
        holder.container.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, topic.backgroundColor))

        if (topic.imageRes != 0) {
            holder.image.visibility = View.VISIBLE
            holder.image.setImageResource(topic.imageRes)
        } else {
            holder.image.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = topics.size
}
