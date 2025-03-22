package com.example.taskexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskexample.BirdsAdapter.BirdViewHolder
import com.google.android.material.imageview.ShapeableImageView

class VegesAdapter(private val veges:List<Animal>):
    RecyclerView.Adapter<VegesAdapter.VegesViewHolder>() {

    class VegesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageVeges: ShapeableImageView = view.findViewById(R.id.imageveges)
        val textVegesName: TextView = view.findViewById(R.id.textvegesName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_birds, parent, false)
        return VegesViewHolder(view)
    }

    override fun onBindViewHolder(holder: VegesViewHolder, position: Int) {
        val birds = veges[position]
        holder.imageVeges.setImageResource(birds.imageResId)
        holder.textVegesName.text = birds.name
    }

    override fun getItemCount() = veges.size
}