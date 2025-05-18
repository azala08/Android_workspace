package com.example.mymeditation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.R
import com.example.mymeditation.activity.PlayOptionActivity
import com.example.mymeditation.model.Sleep


class SleepAdapter(private val sleep: List<Sleep>) : RecyclerView.Adapter<SleepAdapter.SleepViewHolder>() {

    inner class SleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvSleepCategoryTitle)
        val min: TextView = itemView.findViewById(R.id.tvSleepCategorySubtitle)
        val icon: ImageView = itemView.findViewById(R.id.imgSleepCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sleep_design, parent, false)
        return SleepViewHolder(view)
    }

    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        val item = sleep[position]
        holder.title.text = item.title
        holder.icon.setImageResource(item.imageRes)
        holder.min.text = item.min

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PlayOptionActivity::class.java)
            intent.putExtra("sleepItem", item)
            holder.itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int = sleep.size
}