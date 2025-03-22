package com.example.taskexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskexample.BirdsAdapter.BirdViewHolder
import com.google.android.material.imageview.ShapeableImageView

class FruitsAdapter(private val fruit:List<Animal>):
    RecyclerView.Adapter<FruitsAdapter.FruitViewHolder>() {

        class FruitViewHolder(view: View): RecyclerView.ViewHolder(view)
        {
            val imageFruit: ShapeableImageView = view.findViewById(R.id.imageFruit)
            val textFruitName: TextView = view.findViewById(R.id.textFruitName)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitsAdapter.FruitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fruits, parent, false)
        return FruitViewHolder(view)
    }

    override fun onBindViewHolder(holder: FruitsAdapter.FruitViewHolder, position: Int) {
        val birds = fruit[position]
        holder.imageFruit.setImageResource(birds.imageResId)
        holder.textFruitName.text = birds.name
    }

    override fun getItemCount()= fruit.size

}