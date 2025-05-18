package com.example.mymeditation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.R
import com.example.mymeditation.model.Home

class HomeAdapter(private val home: List<Home>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvHomeFocus)
        val min: TextView = itemView.findViewById(R.id.tvHomeMin)
        val icon: ImageView = itemView.findViewById(R.id.imgHomeCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_design, parent, false)

        return HomeViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val home = home[position]
        holder.title.text = home.title
        holder.icon.setImageResource(home.imageRes)
        holder.min.text = home.min

    }

    override fun getItemCount(): Int =home.size
}