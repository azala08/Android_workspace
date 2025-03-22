package com.example.retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Apiinterface
{
    @FormUrlEncoded
    @POST("productinsert.php")
    fun insertdata
                (
        @Field("product_name") product_name:String,
        @Field("product_price") product_price:String,
        @Field("product_description") product_description:String
    ): Call<Void>
}