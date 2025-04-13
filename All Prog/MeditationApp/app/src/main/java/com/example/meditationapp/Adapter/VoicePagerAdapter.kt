package com.example.meditationapp.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.meditationapp.Fragment.VoiceFragment

class VoicePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return VoiceFragment.newInstance(if (position == 0) "MALE" else "FEMALE")
    }
}
