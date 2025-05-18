package com.example.mymeditation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.LikedSleepViewModel
import com.example.mymeditation.R
import com.example.mymeditation.activity.SleepMusicPlayActivity
import com.example.mymeditation.adapter.SleepLikedAdapter
import com.example.mymeditation.model.Sleep
import kotlinx.coroutines.launch


class SleepMyCategoryFragment : Fragment() {

    private lateinit var viewModel: LikedSleepViewModel
    private lateinit var adapter: SleepLikedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_sleep_my_category, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvSleepLikedMusic)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this)[LikedSleepViewModel::class.java]

        adapter = SleepLikedAdapter(
            onCancelClick = { sleep ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.unlike(sleep)
                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
                }
            }
        ).also { sleepAdapter ->
            sleepAdapter.setOnItemClickListener { selectedSleep ->
                val likedList = adapter.currentList

                // Convert LikedSleepEntity list to Sleep list
                val sleepList = likedList.map { likedItem ->
                    Sleep(
                      // Mapping 'audioResId' as 'id'
                        imageRes = 0,               // Placeholder, replace with actual image resource if available
                        title = likedItem.title,    // Using the title from LikedSleepEntity
                        min = "0:00",               // Placeholder, replace with actual duration or logic to determine it
                        playOptionImageRes = 0,     // Placeholder, replace with actual image resource if needed
                        audioResId = likedItem.audioResId // Directly using audioResId
                    )
                }

                val position = sleepList.indexOfFirst { it.audioResId == selectedSleep.audioResId }

                val intent = Intent(requireContext(), SleepMusicPlayActivity::class.java).apply {
                    putParcelableArrayListExtra("sleep_music_list", ArrayList(sleepList))
                    putExtra("sleep_current_position", position)
                }
                startActivity(intent)
            }
        }

        recyclerView.adapter = adapter

        viewModel.likedMusic.observe(viewLifecycleOwner) { likedList ->
            adapter.submitList(likedList)
        }

        return view
    }
}
