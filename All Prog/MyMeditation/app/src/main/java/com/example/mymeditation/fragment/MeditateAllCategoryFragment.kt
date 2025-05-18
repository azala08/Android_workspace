package com.example.mymeditation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mymeditation.R
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.adapter.MeditateAdapter
import com.example.mymeditation.databinding.FragmentMeditateAllCategoryBinding
import com.example.mymeditation.databinding.FragmentMeditateBinding
import com.example.mymeditation.model.Meditate


class MeditateAllCategoryFragment : Fragment() {


    lateinit var binding: FragmentMeditateAllCategoryBinding
    private lateinit var category: MutableList<Meditate>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeditateAllCategoryBinding.inflate(layoutInflater)
        val view = binding.root

        SetStatusBar.fromFragment(this,false)

        category = mutableListOf()

        category.add(Meditate(R.drawable.calm, resources.getString(R.string._7_days_of_calm)))
        category.add(Meditate(R.drawable.anxieous, resources.getString(R.string.anxiet_release)))
        category.add(Meditate(R.drawable.nature, resources.getString(R.string._7_days_of_calm)))
        category.add(Meditate(R.drawable.sea, resources.getString(R.string.anxiet_release)))
        category.add(Meditate(R.drawable.calm, resources.getString(R.string._7_days_of_calm)))
        category.add(Meditate(R.drawable.anxieous, resources.getString(R.string.anxiet_release)))
        category.add(Meditate(R.drawable.nature, resources.getString(R.string._7_days_of_calm)))
        category.add(Meditate(R.drawable.sea, resources.getString(R.string.anxiet_release)))

        val bottomLayout: RecyclerView.LayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvStaggeredGrid.layoutManager = bottomLayout

        val meditate = MeditateAdapter(requireActivity(), category)
        binding.rvStaggeredGrid.adapter = meditate

        return view
    }
}

//as per shown this file i want to set fragmentmeditatecategory.xml file in  meditateallcategoryfragment so
//when i click on meditate then it waill show meditatefragment and in that recyclerview as per shown chategpry all so it will automatic show data in all category
//basically set data in recyclerview of meditate fragment inside the all category