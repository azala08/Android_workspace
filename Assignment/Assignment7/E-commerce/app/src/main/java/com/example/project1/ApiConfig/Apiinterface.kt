package com.example.project1.ApiConfig

import com.example.project1.Admin.Model.PaymentModel
import com.example.project1.Admin.Model.data2
import com.example.project1.Model.CartModel
import com.example.project1.Model.CategoryModel
import com.example.project1.Model.DashboardModel
import com.example.project1.Model.SignupModel
import com.example.project1.Model.WishlistModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Apiinterface
{
    @FormUrlEncoded
    @POST("user_signup.php")
    fun signup
                (
        @Field("user_first_name") user_first_name:String,
        @Field("user_last_name") user_last_name:String,
        @Field("user_phone") user_phone:String,
        @Field("user_gender") user_gender:String,
        @Field("user_email") user_email:String,
        @Field("user_password") user_password:String,
        @Field("type") type:String
    ): Call<Void>


    @FormUrlEncoded
    @POST("user_login.php")
    fun signin
                (
        @Field("user_phone") user_phone:String,
        @Field("user_password") user_password:String
    ): Call<SignupModel>

    @GET("dashboard_view.php")
    fun dashboardviewdata() : Call<List<DashboardModel>>

    @FormUrlEncoded
    @POST("category_view.php")
    fun categoryviewdata
                (
        @Field("data") data: Int?,
    ): Call<List<CategoryModel>>

    @FormUrlEncoded
    @POST("add_data_to_wishlist.php")
    fun addwishlist
                (
        @Field("gift_name") gift_name:String?,
        @Field("gift_description") gift_description:String?,
        @Field("gift_price") gift_price:String?,
        @Field("image") image:String?,
        @Field("mobile") mobile:String?,
    ): Call<Void>

    @FormUrlEncoded
    @POST("viewwishlist.php")
    fun viewwishlistdata
                (
        @Field("mobile") data: String?,
    ): Call<List<WishlistModel>>

    @FormUrlEncoded
    @POST("add_data_to_cart.php")
    fun addtocart
                (
        @Field("gift_name") gift_name:String?,
        @Field("gift_description") gift_description:String?,
        @Field("gift_price") gift_price:String?,
        @Field("image") image:String?,
        @Field("mobile") mobile:String?,
    ): Call<Void>

    @FormUrlEncoded
    @POST("deletewishlist.php")
    fun deletewishlistdata
                (
        @Field("id") id: Int?,
    ): Call<Void>

    @FormUrlEncoded
    @POST("viewcart.php")
    fun viewcartdata
                (
        @Field("mobile") data: String?,
    ): Call<List<CartModel>>

    @FormUrlEncoded
    @POST("deletecart.php")
    fun deletecartdata
                (
        @Field("id") id: Int?,
    ): Call<Void>

    @FormUrlEncoded
    @POST("paymentadd.php")
    fun paymentadd
                (
        @Field("pname") pname:String,
        @Field("pprice") pprice:String,
        @Field("pdes") pdes:String,
        @Field("pimage") pimage:String,
        @Field("mobile") mobile:String
    ): Call<Void>

    @Multipart
    @POST("addcategory.php")
    suspend fun uploadImage(
        @Part url: MultipartBody.Part,
        @Part("name") emp_name: RequestBody?,
    ): ResponseBody

    @Multipart
    @POST("addproduct.php")
    suspend fun uploadImage2(
        @Part url: MultipartBody.Part,
        @Part("name") name: RequestBody?,
        @Part("price") price: RequestBody?,
        @Part("description") description: RequestBody?,
        @Part("c_id") c_id: RequestBody?,
    ): ResponseBody


    @GET("paymentview.php")
    fun paymentview() : Call<List<PaymentModel>>

    @GET("dashboard_view.php")
    fun dashboard_viewdata2():Call<List<data2>>

}