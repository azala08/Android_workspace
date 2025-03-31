package com.example.project1.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Activity.CategoryViewActivity
import com.example.project1.Model.DashboardModel
import com.example.project1.R
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class DashboardAdapter(var context:Context,var dashboardlist:MutableList<DashboardModel>) : RecyclerView.Adapter<DashboardView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardView
    {
        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design_dashboard,parent,false)
        return DashboardView(view)
    }

    override fun getItemCount(): Int
    {
        return dashboardlist.size
    }

    override fun onBindViewHolder(holder: DashboardView, position: Int)
    {
        holder.textView.setText(dashboardlist.get(position).name)
        // holder.imageview2.set(dashboardlist.get(position).url)
        Picasso.get().load(dashboardlist.get(position).url).into(holder.imageview2)

        holder.itemView.setOnClickListener {

            var i = Intent(context, CategoryViewActivity::class.java)
            i.putExtra("did",dashboardlist.get(position).id)
            i.putExtra("dname",dashboardlist.get(position).name)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)

        }
    }

}
class DashboardView(dashboardview: View) : RecyclerView.ViewHolder(dashboardview)
{
    var textView: MaterialTextView = dashboardview.findViewById(R.id.dashboard_txt)
    var imageview2: ImageView = dashboardview.findViewById(R.id.dashboard_img)

}