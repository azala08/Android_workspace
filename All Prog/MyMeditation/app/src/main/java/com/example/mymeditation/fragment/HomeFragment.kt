package com.example.mymeditation.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.activity.CourseDetailActivity
import com.example.mymeditation.adapter.HomeAdapter
import com.example.mymeditation.model.Home
import com.example.mymeditation.R
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.activity.ChooseTopicActivity
import com.example.mymeditation.databinding.FragmentHomeBinding
import java.util.Calendar

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        SetStatusBar.fromFragment(this,false)

        val home = listOf(
            Home(R.drawable.green_card, "Focus", "mymeditation • 3-10 MIN"),
            Home(R.drawable.yellow_card, "Happiness", "mymeditation • 3-10 MIN"),
            Home(R.drawable.green_card, "Focus", "mymeditation • 3-10 MIN"),
            Home(R.drawable.yellow_card, "Happiness", "mymeditation • 3-10 MIN")
        )

        recyclerView = binding.rvItem
        recyclerView.layoutManager = GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, false)
        adapter = HomeAdapter(home)
        recyclerView.adapter = adapter

        binding.btnStart.setOnClickListener {
            val intent = Intent(requireContext(), CourseDetailActivity::class.java)
            startActivity(intent)
        }

        binding.btnStartY.setOnClickListener {
            startActivity(Intent(requireContext(), CourseDetailActivity::class.java))
        }

        // Fetch name from SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val name = sharedPref.getString("name", "") ?: ""

        // Get greeting based on time
        val greetingText = getGreetingMessage()
        binding.greeting.text = "$greetingText, $name"


        return view
    }

    private fun getGreetingMessage(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..20 -> "Good Evening"
            else -> "Good Night"
        }
    }

    fun onBackPressed(): Boolean {
        val intent = Intent(requireContext(), ChooseTopicActivity::class.java)
        startActivity(intent)
        return true
    }

}

