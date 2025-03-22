package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity()
{
    private lateinit var adapter: MyAdapter
    private val weatherList: MutableList<Model> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MyAdapter(this, weatherList)
        recyclerView.adapter = adapter

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        fetchWeatherData("Rajkot", progressBar)
    }

    private fun fetchWeatherData(city: String, progressBar: ProgressBar) {
        val apiClient = ApiClient.getapiclient()
        val apiInterface = apiClient.create(Apiinterface::class.java)

        val call = apiInterface.getdata(city, "78f5b797538848ddc9fae05b60eeb011", "metric")

        call.enqueue(object : Callback<Model> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val weatherData = response.body()
                    if (weatherData != null) {
                        weatherList.clear()
                        weatherList.add(weatherData)
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.e("WMApp", "No data returned from the API")
                        showDummyData()
                    }
                } else {
                    Log.e("WMApp", "API response was not successful: ${response.code()}")
                    showDummyData()
                }
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e("WMApp", "Network request failed: ${t.message}")
                showDummyData()
            }
        })
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun showDummyData() {
        val dummyWeather = Model(
            name = "Dummy City",
            main = Main(temp = 22.5, humidity = 60),
            weather = listOf(Weather(description = "Clear sky", icon = "01d")),
            wind = Wind(speed = 5.5)
        )

        weatherList.clear()
        weatherList.add(dummyWeather)
        adapter.notifyDataSetChanged()
    }
}