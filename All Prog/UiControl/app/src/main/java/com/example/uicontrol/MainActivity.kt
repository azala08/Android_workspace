package com.example.uicontrol

import android.os.Bundle
import android.widget.GridView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity()
{
    lateinit var listView: GridView
    lateinit var list:MutableList<Model>
    lateinit var f1:FloatingActionButton
    lateinit var sliderLayout: SliderLayout
    var map = HashMap<String,Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = this.findViewById(R.id.list)
        list = ArrayList()
        sliderLayout = findViewById(R.id.slider)
        f1 = findViewById(R.id.f1)

        map.put("Product1",R.drawable.maahi4)
        map.put("Product2",R.drawable.maahi5)

        for(i in map.keys)
        {
            var txtslider = TextSliderView(this)

            txtslider.image(map.get(i)!!)

            sliderLayout.addSlider(txtslider)

        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.FlipPage)

        f1.setOnClickListener {

            Snackbar.make(it,"Hello",Snackbar.LENGTH_LONG).show()
            //Toast.makeText(applicationContext, "OK", Toast.LENGTH_SHORT).show()

        }

        list.add(Model(R.drawable.butter,"Butter","75"))
        list.add(Model(R.drawable.cream,"Cream","160"))
        list.add(Model(R.drawable.cheeseslice,"Cheeseslice","65"))
        list.add(Model(R.drawable.cheese,"cheese","150"))
        list.add(Model(R.drawable.chocolate,"Chocolate","250"))
        list.add(Model(R.drawable.cool,"Cool","50"))
        list.add(Model(R.drawable.cheesecube,"Cheesecube","35"))

        var adapter = MyAdapter(applicationContext,list)
        listView.adapter=adapter

    }
}
