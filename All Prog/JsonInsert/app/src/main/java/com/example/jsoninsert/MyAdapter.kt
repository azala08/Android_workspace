package com.example.jsoninsert

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter (var context: Context, var list:MutableList<Model>) : BaseAdapter()
{
    override fun getCount(): Int
    {
        return list.size
    }

    override fun getItem(p0: Int): Any
    {
        return list.get(p0)
    }

    override fun getItemId(p0: Int): Long
    {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View
    {
        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design,p2,false)

        var txt1: TextView = view.findViewById(R.id.txtid)
        var txt2: TextView = view.findViewById(R.id.txtname)
        var txt3: TextView = view.findViewById(R.id.txtprice)
        var txt4: TextView = view.findViewById(R.id.txtdes)


        txt1.setText(list.get(p0).id)
        txt2.setText(list.get(p0).product_name)
        txt3.setText(list.get(p0).product_price)
        txt4.setText(list.get(p0).product_description)

        return view
    }
}