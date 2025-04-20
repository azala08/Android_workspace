package com.example.meditationapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meditationapp.Model.TabItem
import com.example.meditationapp.R


class TabAdapter(private val tabList: List<TabItem>) :
    RecyclerView.Adapter<TabAdapter.TabViewHolder>() {

    inner class TabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.tab_icon)
        val title: TextView = itemView.findViewById(R.id.tab_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tab, parent, false)
        return TabViewHolder(view)
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        val item = tabList[position]
        holder.icon.setImageResource(item.iconResId)
        holder.title.text = item.title
    }

    override fun getItemCount() = tabList.size
}
