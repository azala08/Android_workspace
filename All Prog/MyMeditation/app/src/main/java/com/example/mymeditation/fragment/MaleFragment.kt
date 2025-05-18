package com.example.mymeditation.fragment

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
import com.example.mymeditation.databinding.FragmentMaleBinding
import com.example.mymeditation.service.MusicService


class MaleFragment : Fragment() {

    private lateinit var binding: FragmentMaleBinding
    private lateinit var adapter: MusicItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMaleBinding.inflate(inflater, container, false)

        SetStatusBar.fromFragment(this,false)

        val musicList = mutableListOf(
            MusicItem("focus_male", "Focus Attention", "10 MIN", false, R.drawable.fill_play,"", R.raw.hobbits),
            MusicItem("scan_male", "Body Scan", "5 MIN", false, R.drawable.play,"", R.raw.pathofharmony),
            MusicItem("happiness_male", "Making Happiness", "3 MIN", false, R.drawable.play,"", R.raw.calmlack)
        )

        val layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.rvMaleVoice.layoutManager = layoutManager

        adapter = MusicItemAdapter(requireActivity(), musicList)
        binding.rvMaleVoice.adapter = adapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.onResume()  // Ensure receiver is registered again
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
