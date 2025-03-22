package com.example.apnibook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

data class ClientItem(val imageResource: Int, val name: String, val phone: String)

class ClientAdapter(
    private val clientItems: List<ClientItem>,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    class ClientViewHolder(itemView: View, private val onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val imageView: ImageView = itemView.findViewById(R.id.clientImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val phoneTextView: TextView = itemView.findViewById(R.id.phoneTextView)

        init {
            cardView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_client, parent, false)
        return ClientViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val clientItem = clientItems[position]
        holder.imageView.setImageResource(clientItem.imageResource)
        holder.nameTextView.text = clientItem.name
        holder.phoneTextView.text = clientItem.phone
    }

    override fun getItemCount() = clientItems.size
}