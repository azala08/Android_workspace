package com.example.whatsapp_menu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyAdapter(fm:FragmentManager) : FragmentStatePagerAdapter(fm)
{
    var listfragment:ArrayList<Fragment> = ArrayList()
    var listtitle:ArrayList<String> = ArrayList()

    override fun getCount(): Int
    {
        return listfragment.size
    }
    override fun getItem(position: Int): Fragment
    {
        return listfragment.get(position)
    }
    override fun getPageTitle(position: Int): CharSequence?
    {
        return listtitle.get(position)
    }
    fun setdata(fm:Fragment,data:String)
    {
        listtitle.add(data)
        listfragment.add(fm)
    }
}