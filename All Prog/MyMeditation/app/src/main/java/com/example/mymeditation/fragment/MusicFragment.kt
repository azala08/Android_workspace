package com.example.mymeditation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeditation.R
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.activity.HomeActivity
import com.example.mymeditation.adapter.SleepAdapter
import com.example.mymeditation.databinding.FragmentMusicBinding
import com.example.mymeditation.model.Sleep


class MusicFragment : Fragment() {

    lateinit var binding: FragmentMusicBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SleepAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMusicBinding.inflate(layoutInflater)
        val view = binding.root

        SetStatusBar.fromFragment(this,false)




        val sleep = listOf(
            Sleep(R.drawable.night_iceland, "Night Island", "45 MIN • SLEEP MUSIC", R.drawable.night_island_playoption,R.raw.hobbits),
            Sleep(R.drawable.sweet_sleep, "Sweet Sleep", "45 MIN • SLEEP MUSIC", R.drawable.sweet_sleep_playoption,R.raw.harrypotter),
            Sleep(R.drawable.good_night, "Good Night", "45 MIN • SLEEP MUSIC", R.drawable.good_night_playoption,R.raw.hobbits),
            Sleep(R.drawable.moon_cloud, "Moon Clouds", "45 MIN • SLEEP MUSIC", R.drawable.moon_clouds_playoption,R.raw.carefree),
            Sleep(R.drawable.night_iceland, "Night Island", "45 MIN • SLEEP MUSIC", R.drawable.night_island_playoption,R.raw.hobbits),
            Sleep(R.drawable.sweet_sleep, "Sweet Sleep", "45 MIN • SLEEP MUSIC", R.drawable.sweet_sleep_playoption,R.raw.harrypotter),
            Sleep(R.drawable.good_night, "Good Night", "45 MIN • SLEEP MUSIC", R.drawable.good_night_playoption,R.raw.hobbits),
            Sleep(R.drawable.moon_cloud, "Moon Clouds", "45 MIN • SLEEP MUSIC", R.drawable.moon_clouds_playoption,R.raw.carefree)
        )

        recyclerView = binding.rvMusicFragment
        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        adapter = SleepAdapter(sleep)
        recyclerView.adapter = adapter

        binding.imgSleepMusicBackArrow.setOnClickListener {
            activity?.let {
                val intent = Intent(it, HomeActivity::class.java)
                it.startActivity(intent)
            }
        }


    onBackPressed()

        return view

    }


    private fun onBackPressed()
    {

    }
}





