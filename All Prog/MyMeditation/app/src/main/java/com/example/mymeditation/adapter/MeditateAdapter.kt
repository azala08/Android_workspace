package com.example.mymeditation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.databinding.MeditattionItemBinding
import com.example.mymeditation.model.Meditate

class MeditateAdapter (
    var context : FragmentActivity,
    private var recyclerItems: MutableList<Meditate>)
    : RecyclerView.Adapter<MeditateAdapter.FragmentRecyclerBottomViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentRecyclerBottomViewHolder
    {
        val view = MeditattionItemBinding.inflate(
            LayoutInflater.from(context),
            parent,false)
        return FragmentRecyclerBottomViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        return recyclerItems.size
    }

    override fun onBindViewHolder(holder: FragmentRecyclerBottomViewHolder, position: Int)
    {
        holder.binding.imgMeditateCard.setImageResource(recyclerItems[position].iconResId)
        holder.binding.tvMeditateCard.text = recyclerItems[position].title
    }

    inner class FragmentRecyclerBottomViewHolder(val binding : MeditattionItemBinding)
        : RecyclerView.ViewHolder(binding.root)

}