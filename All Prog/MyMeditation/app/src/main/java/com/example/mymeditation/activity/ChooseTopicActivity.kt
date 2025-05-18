package com.example.mymeditation.activity


import android.os.Bundle
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mymeditation.R
import com.example.mymeditation.databinding.ActivityChooseTopicBinding
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.adapter.TopicAdapter
import com.example.mymeditation.model.ModelChooseTopic

class ChooseTopicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseTopicBinding
    private lateinit var recyclerItem: MutableList<ModelChooseTopic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseTopicBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        SetStatusBar.fromActivity(this,false)

        applyWindowInsets(binding.chooseTopicmain)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerItem = mutableListOf(
            ModelChooseTopic(R.drawable.purple_stress, "Reduce Stress", R.color.purple_text),
            ModelChooseTopic(R.drawable.red, "Improve\nPerformance", R.color.red),
            ModelChooseTopic(R.drawable.yellows, "Reduce Anxiety", R.color.orange),
            ModelChooseTopic(R.drawable.orange, "Increase\nHappiness", R.color.orange),
            ModelChooseTopic(R.drawable.black, "Better Sleep", R.color.black_text),
            ModelChooseTopic(R.drawable.green, "Personal\nGrowth", R.color.purple_text),
            ModelChooseTopic(R.drawable.pink, "Improve\nPerformance", R.color.orange),
            ModelChooseTopic(R.drawable.dark_purple, "Reduce Stress", R.color.red)
        )

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.topicRecyclerView.layoutManager = layoutManager

        val topicAdapter = TopicAdapter(this, recyclerItem) // Use `this` instead of applicationContext
        binding.topicRecyclerView.adapter = topicAdapter
    }

    private fun applyWindowInsets(rootView: View) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBarsInsets.left,
                systemBarsInsets.top,
                systemBarsInsets.right,
                systemBarsInsets.bottom
            )
            insets
        }
    }
    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}

