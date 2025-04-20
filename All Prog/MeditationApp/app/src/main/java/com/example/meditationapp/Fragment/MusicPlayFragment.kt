package com.example.meditationapp.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meditationapp.Activity.MusicPlayActivity
import com.example.meditationapp.Adapter.MusicAdapter

import com.example.meditationapp.Model.MusicModel
import com.example.meditationapp.R


class MusicPlayFragment : Fragment() {
    private lateinit var musicList: List<MusicModel>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music_play, container, false)

        musicList = listOf(
            MusicModel("Focus Attention", "7 DAYS OF CALM", R.raw.sample_audio),
            MusicModel("Deep Breath", "7 DAYS OF CALM", R.raw.lion),
            MusicModel("Evening Calm", "7 DAYS OF CALM", R.raw.tiger)
        )

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MusicAdapter(musicList) { music ->
            val intent = Intent(requireContext(), MusicPlayActivity::class.java).apply {
                putExtra("AUDIO_RES_ID", music.audioResId)
                putExtra("TITLE", music.title)
                putExtra("SUBTITLE", music.subtitle)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        return view
    }
}