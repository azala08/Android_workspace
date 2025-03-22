package com.example.project1.ApiConfig

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient
{
    lateinit var retrofit: Retrofit
    var BASE_URL="https://prakrutitech.buzz/AndroidAPI/"

    //Connection Established
    fun getconnect() : Retrofit
    {

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        return  retrofit
    }

}