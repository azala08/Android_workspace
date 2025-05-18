package com.example.mymeditation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymeditation.model.Meditate
import com.example.mymeditation.model.MeditateCategory
import com.example.mymeditation.R
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.adapter.SleepCategoryAdapter
import com.example.mymeditation.databinding.FragmentSleepBinding

class SleepFragment : Fragment() {
    private lateinit var binding : FragmentSleepBinding
    private lateinit var bottomRecyclerViewItems : MutableList<Meditate>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentSleepBinding.inflate(layoutInflater)
        val view = binding.root

        SetStatusBar.fromFragment(this,false)


        parentFragmentManager.beginTransaction()
            .replace(R.id.frameSleepCategory, SleepAllCategoryFragment())
            .commit()

        bottomRecyclerViewItems = mutableListOf()

        val categoryItems = listOf(
            MeditateCategory(R.drawable.ic_all, getString(R.string.all)),
            MeditateCategory(R.drawable.ic_like, getString(R.string.my) ),
            MeditateCategory(R.drawable.ic_anxious, getString(R.string.anxious)),
            MeditateCategory(R.drawable.ic_m_sleep, getString(R.string.sleep)) ,
            MeditateCategory(R.drawable.ic_kids, getString(R.string.kids))
        )

        val categoryAdapter = SleepCategoryAdapter(categoryItems)
        binding.rvSleepCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSleepCategory.adapter = categoryAdapter


        categoryAdapter.setOnItemClickListener { category ->
            if (category.title == getString(R.string.all)) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameSleepCategory, SleepAllCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (category.title == getString(R.string.my)){
                Toast.makeText(view.context,"My Fragment", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameSleepCategory, SleepMyCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (category.title == getString(R.string.anxious)){
                Toast.makeText(view.context,"Anxious Fragment", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameSleepCategory, SleepAnxiousCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (category.title == getString(R.string.sleep)) {
                Toast.makeText(view.context,"Sleep Fragment", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameSleepCategory, SleepSleepCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (category.title == getString(R.string.kids)){
                Toast.makeText(view.context,"Kids Fragment", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameSleepCategory, SleepKidsCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        return view
    }
}
