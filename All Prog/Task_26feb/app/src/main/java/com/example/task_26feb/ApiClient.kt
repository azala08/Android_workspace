package com.example.task_26feb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient
{
    companion object
    {
        lateinit var retrofit: Retrofit
    }

    fun getapiclient():Retrofit
        {
            retrofit = Retrofit.Builder()
                .baseUrl("https://prakrutitech.buzz/Practical_Task/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }

}