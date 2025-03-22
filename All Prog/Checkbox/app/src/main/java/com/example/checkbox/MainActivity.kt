package com.example.checkbox

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uicontrolsex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener,
    AdapterView.OnItemSelectedListener {

    lateinit var binding:ActivityMainBinding
    var city = arrayOf("Rajkot","Ahmedabad","Baroda")
    var data = arrayOf("java","kotlin","react","android","flutter")

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.f4.setOnCheckedChangeListener(this)

        binding.rb1.setOnCheckedChangeListener(this)
        binding.rb2.setOnCheckedChangeListener(this)


        var adapter = ArrayAdapter(applicationContext,android.R.layout.select_dialog_item,city)
        binding.spin.adapter=adapter

        binding.spin.setOnItemSelectedListener(this)

        var adapter2 = ArrayAdapter(applicationContext,android.R.layout.select_dialog_item,data)
        binding.auto1.threshold=2
        binding.auto1.setAdapter(adapter2)

        binding.btn1.setOnClickListener {

            var total = 0
            var builder = StringBuilder()

            if(binding.f1.isChecked)
            {
                total+=100
                builder.append("\n Pizza @ Rs.100")
            }
            if(binding.f2.isChecked)
            {
                total+=70
                builder.append("\n Burger @ Rs.70")
            }
            if(binding.f3.isChecked)
            {
                total+=120
                builder.append("\n Coffee @ Rs.120")
            }

            builder.append("\n"+builder.toString()+"Total : "+total)

            Toast.makeText(applicationContext, ""+builder.toString(), Toast.LENGTH_SHORT).show()

            Log.d("BILL",builder.toString())


        }

        binding.rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            Toast.makeText(applicationContext, ""+ratingBar.rating, Toast.LENGTH_SHORT).show()

        }

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean)
    {
        if(binding.f4.isChecked)
        {
            Toast.makeText(applicationContext, "checked", Toast.LENGTH_SHORT).show()
        }

        else if(binding.rb1.isChecked==true)
        {
            Toast.makeText(applicationContext, "Male", Toast.LENGTH_SHORT).show()
        }
        else if(binding.rb1.isChecked==false)
        {
            // Toast.makeText(applicationContext, "Male", Toast.LENGTH_SHORT).show()
        }
        else if(binding.rb2.isChecked==true)
        {
            Toast.makeText(applicationContext, "Female", Toast.LENGTH_SHORT).show()
        }
        else if(binding.rb2.isChecked==false)
        {
            // Toast.makeText(applicationContext, "Male", Toast.LENGTH_SHORT).show()
        }

        else
        {
            Toast.makeText(applicationContext, "unchecked", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        if(position==0)
        {

        }
        if(position==1)
        {

        }
        if(position==2)
        {

        }

        Toast.makeText(applicationContext, ""+city[position], Toast.LENGTH_SHORT).show()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}