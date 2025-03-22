package com.example.module4_assignment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.module4_assignment3.R

class SettingFragment : Fragment()
{

    lateinit var extraStringS : TextView
    lateinit var btn : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var SettingFragment = inflater.inflate(R.layout.fragment_setting, container, false)

        extraStringS = SettingFragment.findViewById(R.id.extratxtS)
        btn = SettingFragment.findViewById(R.id.btnsetting)

        var bundle = arguments
        extraStringS.text = bundle!!.getString("String")

        return SettingFragment
    }
}