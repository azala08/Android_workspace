package com.example.a18_1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class MainActivity : AppCompatActivity()
{
    lateinit var btn1: Button
    lateinit var btn2:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        btn1.setOnClickListener {

            var d1 = MyDatePicker()
            d1.show(supportFragmentManager,"select date")

        }
        btn2.setOnClickListener {

            var d1 = MyTimePicker()
            d1.show(supportFragmentManager,"select time")
        }
    }
}

class MyDatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        var c= Calendar.getInstance()
        var day = c.get(Calendar.DAY_OF_MONTH)
        var year = c.get(Calendar.YEAR)
        var month =  c.get(Calendar.MONTH)

        return DatePickerDialog(requireActivity(),this,year,month,day)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int)
    {
        Toast.makeText(requireActivity(), ""+p1+"-"+p2+"-"+p3, Toast.LENGTH_SHORT).show()
    }

}
class MyTimePicker : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        var c= Calendar.getInstance()
        var hour = c.get(Calendar.HOUR_OF_DAY)
        var min = c.get(Calendar.MINUTE)


        return TimePickerDialog(requireActivity(),this,hour,min,true)
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int)
    {
        Toast.makeText(requireActivity(), ""+p1+":"+p2, Toast.LENGTH_SHORT).show()
    }

}