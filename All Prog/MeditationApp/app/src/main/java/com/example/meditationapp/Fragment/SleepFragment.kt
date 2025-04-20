package com.example.meditationapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meditationapp.Adapter.MusicAdapter
import com.example.meditationapp.Adapter.TabAdapter
import com.example.meditationapp.Model.MusicItem
import com.example.meditationapp.Model.TabItem
import com.example.meditationapp.R


class SleepFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sleep, container, false)

        val tabRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_tabs)
        val musicRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_music)

        val tabs = listOf(
            TabItem(R.drawable.all, "All"),
            TabItem(R.drawable.all, "My"),
            TabItem(R.drawable.all, "Anxious"),
            TabItem(R.drawable.all, "Sleep"),
            TabItem(R.drawable.all, "Kids")
        )

        val musics = listOf(
            MusicItem(R.drawable.birds, "Night Island", "45 MIN • SLEEP MUSIC"),
            MusicItem(R.drawable.birds, "Sweet Sleep", "45 MIN • SLEEP MUSIC")
        )

        tabRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tabRecyclerView.adapter = TabAdapter(tabs)

        musicRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        musicRecyclerView.adapter = MusicAdapter(musics)

        return view
    }
}
