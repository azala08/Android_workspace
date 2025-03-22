package com.example.task_26feb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(var context: Context, var list: MutableList<Model>): RecyclerView.Adapter<MyView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.design,parent,false)
        return MyView(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.textView.setText(list.get(position).pname)
        holder.textView2.setText(list.get(position).pprice)
        holder.textView3.setText(list.get(position).pdesc)
        holder.textView4.setText(list.get(position).pstatus)

        Picasso.get()
            .load(list.get(position).pimage)
            .into(holder.imageview)
    }
}

class MyView(view: View):RecyclerView.ViewHolder(view) {

    var textView: TextView = view.findViewById(R.id.itemname)
    var textView2:TextView = view.findViewById(R.id.itemprice)
    var textView3:TextView = view.findViewById(R.id.itemdesc)
    var textView4:TextView = view.findViewById(R.id.itemstatus)
    var imageview : ImageView = view.findViewById(R.id.itemimage)

}