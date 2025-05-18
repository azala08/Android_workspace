package com.example.mymymeditation.fragment

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.adapter.MusicItemAdapter
import com.example.mymeditation.model.MusicItem
import com.example.mymeditation.R
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.databinding.FragmentFemaleBinding

class FemaleFragment : Fragment() {

    private lateinit var binding: FragmentFemaleBinding
    private lateinit var adapter: MusicItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFemaleBinding.inflate(inflater, container, false)

        SetStatusBar.fromFragment(this,false)

        val musicList = mutableListOf(
            MusicItem("focus_female", "Focus Attention Female", "10 MIN", false, R.drawable.play, "",R.raw.breathoflife),
            MusicItem("scan_female", "Body Scan Female", "5 MIN", false, R.drawable.play, "",R.raw.carefree),
            MusicItem("happiness_female", "Making Happiness Female", "3 MIN", false, R.drawable.play,"", R.raw.harrypotter)
        )

        val layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.rvFemaleVoice.layoutManager = layoutManager

        adapter = MusicItemAdapter(requireActivity(), musicList)
        binding.rvFemaleVoice.adapter = adapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.onResume() // Ensure receiver is re-registered
    }

    override fun onPause() {
        adapter.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.cleanup()
    }
}

