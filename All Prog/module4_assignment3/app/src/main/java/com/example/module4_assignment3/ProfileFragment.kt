package com.example.module4_assignment3


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.module4_assignment3.R

class ProfileFragment : Fragment()
{
    lateinit var extraStringP : TextView
    lateinit var btn : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var ProfileFragment = inflater.inflate(R.layout.fragment_profile, container, false)

        extraStringP = ProfileFragment.findViewById(R.id.extratxtP)
        btn = ProfileFragment.findViewById(R.id.btnprofile)

        var bundle = arguments
        extraStringP.text = bundle!!.getString("String")

        btn.setOnClickListener {

            var string = extraStringP.text.toString()

            var settingFragment = SettingFragment()
            var bundle = Bundle().apply { putString("String", string) }
            settingFragment.arguments = bundle
            var fragmentManager = parentFragmentManager
            var transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.frame, settingFragment).commit()
        }

        return ProfileFragment
    }
}