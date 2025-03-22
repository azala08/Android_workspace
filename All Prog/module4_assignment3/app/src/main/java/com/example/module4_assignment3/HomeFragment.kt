package com.example.module4_assignment3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.module4_assignment3.ProfileFragment

class HomeFragment : Fragment()
{
    lateinit var extraStringH : TextView
    lateinit var btn : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var HomeFragment = inflater.inflate(R.layout.fragment_home, container, false)

        extraStringH = HomeFragment.findViewById(R.id.extratxtH)
        btn = HomeFragment.findViewById(R.id.btnhome)

        btn.setOnClickListener {

            var string = extraStringH.text.toString()

            var profileFragment = ProfileFragment()
            var bundle = Bundle().apply { putString("String",string) }
            profileFragment.arguments = bundle
            var fragmentManager = parentFragmentManager
            var transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.frame,profileFragment).commit()
        }

        return HomeFragment
    }
}