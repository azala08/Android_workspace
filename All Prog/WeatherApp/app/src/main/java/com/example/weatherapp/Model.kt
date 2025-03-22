package com.example.weatherapp

class Model(
    val name: String = "",
    val main: Main = Main(),
    val weather: List<Weather> = emptyList(),
    val wind: Wind = Wind()
)


data class Main(
    val temp: Double = 0.0,
    val humidity: Int = 0
)


data class Weather(
    val description: String = "",
    val icon: String = ""
)


data class Wind(
    val speed: Double = 0.0
)