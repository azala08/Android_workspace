package com.example.taskexample

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskexample.AnimalAdapter.AnimalViewHolder
import com.google.android.material.imageview.ShapeableImageView

class BirdsAdapter(private val birds:List<Animal>):
        RecyclerView.Adapter<BirdsAdapter.BirdViewHolder>() {

    class BirdViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val imageBird: ShapeableImageView =view.findViewById(R.id.imagebird)
        val textBirdName: TextView = view.findViewById(R.id.textbirdName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirdViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_birds, parent, false)
        return BirdViewHolder(view)
    }

    override fun onBindViewHolder(holder: BirdViewHolder, position: Int) {
        val birds = birds[position]
        holder.imageBird.setImageResource(birds.imageResId)
        holder.textBirdName.text = birds.name
    }

    override fun getItemCount() = birds.size

}