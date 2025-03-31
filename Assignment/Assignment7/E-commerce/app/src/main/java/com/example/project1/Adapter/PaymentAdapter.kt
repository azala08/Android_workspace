package com.example.project1.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Admin.Model.PaymentModel
import com.example.project1.R
import com.google.android.material.textview.MaterialTextView

class PaymentAdapter(var context: Context?, var mutableList: MutableList<PaymentModel>) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    //lateinit var sharedPreferences: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.payment_layout, parent, false)
        return PaymentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, @SuppressLint("RecyclerView") position: Int)
    {
        // sharedPreferences = context!!.getSharedPreferences("Appproject", Context.MODE_PRIVATE)
        // val mob = sharedPreferences.getString("n1", "")

        holder.textView1.text = mutableList.get(position).pprice
        holder.textView2.text = mutableList.get(position).mobile



    }



    class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: MaterialTextView
        val textView2: MaterialTextView


        init {
            textView1 = itemView.findViewById(R.id.tvName)
            textView2 = itemView.findViewById(R.id.tvPrice)
        }
    }
}