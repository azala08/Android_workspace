package com.example.project1.Admin.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.project1.Activity.AddProductActivity
import com.example.project1.Activity.AddSubProdcutActivity
import com.example.project1.Activity.PaymentViewActivity
import com.example.project1.Admin.Model.AdminModel
import com.example.project1.R

class AdminAdapter(var context:Context,var list:MutableList<AdminModel>) :BaseAdapter()
{
    override fun getCount(): Int
    {
        return list.size
    }

    override fun getItem(position: Int): Any
    {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design_admin_dashboard,parent,false)

        var image:ImageView = view.findViewById(R.id.img)
        var text:TextView = view.findViewById(R.id.txt)

        image.setOnClickListener {

            if(position==0)
            {
                var i = Intent(context, AddProductActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
            if(position==1)
            {
                var i = Intent(context, AddSubProdcutActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
            if(position==2)
            {
                var i = Intent(context, PaymentViewActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }


        }

        image.setImageResource(list.get(position).image)
        text.setText(list.get(position).name)

        return view
    }

}