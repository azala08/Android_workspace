package com.example.project1.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Activity.FullScreenActivity
import com.example.project1.Model.CategoryModel
import com.example.project1.R
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class CategoryAdapter(var context:Context,var categorylist:MutableList<CategoryModel>) : RecyclerView.Adapter<CategoryView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryView
    {
        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design_categoryview,parent,false)
        return CategoryView(view)
    }

    override fun getItemCount(): Int
    {
        return categorylist.size
    }

    override fun onBindViewHolder(holder: CategoryView, position: Int)
    {
        holder.textView1.setText(categorylist.get(position).name)
        holder.textView2.setText(categorylist.get(position).price)
        holder.textView3.setText(categorylist.get(position).description)
        // holder.imageview2.set(dashboardlist.get(position).url)
        Picasso.get().load(categorylist.get(position).url).into(holder.imageview)
        holder.itemView.setOnClickListener {

            var i = Intent(context,FullScreenActivity::class.java)
            i.putExtra("image", categorylist[position].url)
            i.putExtra("name", categorylist[position].name)
            i.putExtra("desc", categorylist[position].description)
            i.putExtra("price", categorylist[position].price)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }

    }

}
class CategoryView(categoryview: View) : RecyclerView.ViewHolder(categoryview)
{
    var textView1: MaterialTextView = categoryview.findViewById(R.id.category_txt)
    var textView2: MaterialTextView = categoryview.findViewById(R.id.category_price)
    var textView3: MaterialTextView = categoryview.findViewById(R.id.category_desc)
    var imageview: ImageView = categoryview.findViewById(R.id.category_img)

}