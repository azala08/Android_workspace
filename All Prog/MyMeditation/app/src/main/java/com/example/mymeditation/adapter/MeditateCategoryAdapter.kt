package com.example.mymeditation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.R
import com.example.mymeditation.model.MeditateCategory

class MeditateCategoryAdapter(private val items: List<MeditateCategory>) : RecyclerView.Adapter<MeditateCategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0


    private var listener: ((MeditateCategory) -> Unit)? = null

    fun setOnItemClickListener(listener: (MeditateCategory) -> Unit) {
        this.listener = listener
    }


    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.imgMeditateIcon)
        val title: TextView = itemView.findViewById(R.id.tvMeditate)
        val card: View = itemView.findViewById(R.id.cvMeditateCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meditate_category_design, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]
        holder.icon.setImageResource(item.iconResId)
        holder.title.text = item.title

        val context = holder.itemView.context
        val isSelected = position == selectedPosition


        //  Properly update card background without breaking shape
        (holder.card as CardView).setCardBackgroundColor(
            ContextCompat.getColor(context, if (isSelected) R.color.purple else R.color.grey)
        )

        holder.title.setTextColor(
            ContextCompat.getColor(context, if (isSelected) R.color.black else R.color.grey)
        )


        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            listener?.invoke(item)
        }
//        adapter.notifyItemChanged(0)

    }

    override fun getItemCount(): Int = items.size

}
