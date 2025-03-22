package com.example.module_4_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Example of sending data to Profile fragment
        view.findViewById<TextView>(R.id.btnToProfile)?.setOnClickListener {
            val bundle = Bundle().apply {
                putString("message", "Hello from Home!")
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_profileFragment,
                bundle
            )
        }
    }
}