package com.example.mymymeditation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymeditation.adapter.MeditateCategoryAdapter
import com.example.mymeditation.model.MeditateCategory
import com.example.mymeditation.R
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.databinding.FragmentMeditateBinding
import com.example.mymeditation.fragment.MeditateAllCategoryFragment
import com.example.mymeditation.fragment.MeditateAnxiousCategoryFragment
import com.example.mymeditation.fragment.MeditateDownloadCategoryFragment
import com.example.mymeditation.fragment.MeditateKidsCategoryFragment
import com.example.mymeditation.fragment.MeditateMyCategoryFragment
import com.example.mymeditation.fragment.MeditateSleepCategoryFragment

class MeditateFragment : Fragment() {

    private lateinit var binding : FragmentMeditateBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentMeditateBinding.inflate(layoutInflater)
        val view = binding.root

        SetStatusBar.fromFragment(this,false)

        parentFragmentManager.beginTransaction()
            .replace(R.id.frameAllCategory, MeditateAllCategoryFragment())
            .commit()

        val categoryItems = listOf(
            MeditateCategory(R.drawable.ic_all, getString(R.string.all)),
            MeditateCategory(R.drawable.ic_like, getString(R.string.my)),
            MeditateCategory(R.drawable.ic_anxious, getString(R.string.anxious)),
            MeditateCategory(R.drawable.ic_m_sleep, getString(R.string.sleep)),
            MeditateCategory(R.drawable.ic_kids, getString(R.string.kids)),
            MeditateCategory(R.drawable.download_category,getString(R.string.download))
        )

        val categoryAdapter = MeditateCategoryAdapter(categoryItems)
        binding.rvMeditateCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMeditateCategory.adapter = categoryAdapter

//        categoryAdapter.setOnItemClickListener { category ->
//            if (category.title == getString(R.string.all)) {
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.frameAllCategoryFragment, MeditateAllCategoryFragment())
//                    .addToBackStack(null)
//                    .commit()
//            }
//
//        }
        categoryAdapter.setOnItemClickListener { category ->
            if (category.title == getString(R.string.all)) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameAllCategory, MeditateAllCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (category.title == getString(R.string.my)){
                Toast.makeText(view.context,"My Fragment", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameAllCategory, MeditateMyCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (category.title == getString(R.string.anxious)){
                Toast.makeText(view.context,"Anxious Fragment", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameAllCategory, MeditateAnxiousCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (category.title == getString(R.string.sleep)){
                Toast.makeText(view.context,"Sleep Fragment", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameAllCategory, MeditateSleepCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (category.title == getString(R.string.kids)){
                Toast.makeText(view.context,"Kids Fragment", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameAllCategory, MeditateKidsCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
            if (category.title == getString(R.string.download)){
                Toast.makeText(view.context,"Download Fragment", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameAllCategory, MeditateDownloadCategoryFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        return view
    }

}