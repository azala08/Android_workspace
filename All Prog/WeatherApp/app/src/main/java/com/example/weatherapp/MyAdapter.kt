package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val context: Context, private val list: List<Model>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.findViewById(R.id.cityname)
        val temperature: TextView = view.findViewById(R.id.temperature)
        val description: TextView = view.findViewById(R.id.description)
        val windSpeed: TextView = view.findViewById(R.id.windspeed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.design, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val weatherData = list[position]
        holder.cityName.text = weatherData.name
        holder.temperature.text = "${weatherData.main.temp}°C"
        holder.description.text = weatherData.weather[0].description
        holder.windSpeed.text = "Wind speed: ${weatherData.wind.speed} m/s"
    }

    override fun getItemCount(): Int {
        return list.size
    }
}