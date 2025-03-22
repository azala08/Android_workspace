package com.example.all_layout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


class BlankFragment2 : Fragment() {

    lateinit var txt1:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_blank2, container, false)

        txt1 = view.findViewById(R.id.txt2)

        txt1.setOnClickListener {

            Toast.makeText(activity, "WELCOME", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}