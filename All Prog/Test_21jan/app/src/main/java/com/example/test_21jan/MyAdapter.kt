package com.example.test_21jan

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(var context: Context, var list:MutableList<Model>,var list1:MutableList<Model>) : BaseAdapter()
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

    @SuppressLint("MissingInflatedId")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View
    {
        var layout = LayoutInflater.from(context)
        var view =layout.inflate(R.layout.design,p2,false)

        var txt1: TextView = view.findViewById(R.id.txt1)
        var image: ImageView = view.findViewById(R.id.image)


        var txt2: TextView = view.findViewById(R.id.txt2)
        var image1: ImageView = view.findViewById(R.id.image1)

        var txt3: TextView = view.findViewById(R.id.txt3)
        var image2: ImageView = view.findViewById(R.id.image2)

        var txt4: TextView = view.findViewById(R.id.txt4)
        var image3: ImageView = view.findViewById(R.id.image3)

        var txt5: TextView = view.findViewById(R.id.txt5)
        var image4: ImageView = view.findViewById(R.id.image4)



        //get data
        txt1.setText(list.get(p0).title)
        image.setImageResource(list.get(p0).image)

        txt2.setText(list1.get(p0).title)
        image1.setImageResource(list.get(p0).image)



        return view


    }

}