package com.example.mymeditation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.LikedMusicViewModel

import com.example.mymeditation.R
import com.example.mymeditation.activity.MusicPlayActivity
import com.example.mymeditation.adapter.LikedMusicAdapter
import com.example.mymeditation.adapter.MusicItemAdapter
import com.example.mymeditation.model.MusicItem
import kotlinx.coroutines.launch


class MeditateMyCategoryFragment : Fragment() {

    private lateinit var viewModel: LikedMusicViewModel
    private lateinit var adapter: LikedMusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_meditate_my_category, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvLikedMusic)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this)[LikedMusicViewModel::class.java]

        adapter = LikedMusicAdapter(
            onCancelClick = { music ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.unlike(music)
                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
                }
            }
        ).also {
            it.setOnItemClickListener { music ->
                val likedList = adapter.currentList.map { item ->
                    MusicItem(
                        id = item.audioResId.toString(),
                        title = item.title,
                        duration =  ("00:00"),
                        imageResId = (R.drawable.music),
                        audioResId = item.audioResId
                    )
                }

                val position = likedList.indexOfFirst { it.audioResId == music.audioResId }

                val intent = Intent(requireContext(), MusicPlayActivity::class.java).apply {
                    putParcelableArrayListExtra("music_list", ArrayList(likedList))
                    putExtra("current_position", position)
                }
                startActivity(intent)
            }
        }

        recyclerView.adapter = adapter

        viewModel.likedMusic.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return view
    }
}

