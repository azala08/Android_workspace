package com.example.task_26feb

import android.graphics.ColorSpace
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Apiinterface
{
    @FormUrlEncoded
    @POST("productinsert.php")
    fun insertdata
                (
       // @Field("pid") product_id:String,
        @Field("pname") product_name:String,
        @Field("pprice") product_price:String,
        @Field("pdesc") product_description: String,
        @Field("pimage") product_image:String,
        @Field("pstatus") product_status:String
    ): Call<Void>

    @FormUrlEncoded
    @POST("Signup.php")
    fun signup
                (
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("surname") surname: String,
        @Field("email") email: String,
        @Field("gender") gender: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String

    ): Call<Void>

    @FormUrlEncoded
    @POST("Signin.php")
    fun signin
                (
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<ColorSpace.Model>

    @GET("productview.php")
    fun getdata() : Call<List<ProductModel>>

    @FormUrlEncoded
    @POST("productupdate.php")
    fun updatedata
                (
        @Field("id") id:Int,
        @Field("pname") pname:String,
        @Field("pprice") pprice:String,
        @Field("pdes") pdes:String,
       // @Field("pimage") pimage:String,
        @Field("pstatus") pstatus:String
    ): Call<Void>

    @FormUrlEncoded
    @POST("productdelete.php")
    fun deletedata
                (
        @Field("id") id:Int,
    ): Call<Void>

    //abstract fun uploaddata(name1: RequestBody, price1: RequestBody, des1: RequestBody, status1: RequestBody, body: MultipartBody.Part): Call<ResponseBody>?

}