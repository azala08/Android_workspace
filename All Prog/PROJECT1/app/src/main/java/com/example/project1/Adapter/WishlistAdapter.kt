package com.example.project1.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Activity.CartActivity
import com.example.project1.Activity.WishlistActivity
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.Model.WishlistModel
import com.example.project1.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistAdapter(var context: Context?, var mutableList: MutableList<WishlistModel>) :
    RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>()
{

    lateinit var apiInterface: Apiinterface
    lateinit var sharedPreferences: SharedPreferences
    //var itemClickListener: ItemClickListener?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.wishlist_adapter_layout, parent, false
        )
        return WishlistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(
        holder: WishlistViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {

        sharedPreferences = context!!.getSharedPreferences("Appproject", Context.MODE_PRIVATE)
        apiInterface = ApiClient().getconnect()!!.create(Apiinterface::class.java)
        val myId = mutableList[position].id
        val giftName = mutableList[position].gift_name
        val giftPrice = mutableList[position].gift_price
        val giftDesc = mutableList[position].gift_description
        val giftImage = mutableList[position].image
//
        holder.textView1.text = giftName
        holder.textView2.text = giftPrice
        Picasso.get().load(giftImage).into(holder.imageView)

        Log.d("MYDATA", mutableList.get(position).gift_name)

        //        holder.itemView.setOnClickListener {
//            var intent = Intent(context, FullScreenActivity::class.java)
//            intent.putExtra("image", giftImage)
//            intent.putExtra("name", giftName)
//            intent.putExtra("desc", giftDesc)
//            intent.putExtra("price", giftPrice)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context!!.startActivity(intent)
//        }

        holder.removeFromWishlistBtn.setOnClickListener {
            val call = apiInterface.deletewishlistdata(myId)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    mutableList.removeAt(position)
                    //itemClickListener!!.onItemClick(holder.adapterPosition, it)
                    Toast.makeText(context, "Removed from wishlist", Toast.LENGTH_SHORT).show()
                    var i = Intent(context, WishlistActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context!!.startActivity(i)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
//
        holder.addToCartBtn.setOnClickListener {

            val mob = sharedPreferences.getString("n1", "")

            val call = apiInterface.addtocart(giftName, giftDesc, giftPrice, giftImage, mob)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()

                    val call2 = apiInterface.deletewishlistdata(myId)
                    call2.enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {

                            var i = Intent(context, CartActivity::class.java)
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context!!.startActivity(i)

                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(context, "Failed2", Toast.LENGTH_SHORT).show()
                        }
                    })


                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

//    interface ItemClickListener {
//        fun onItemClick(position: Int, view: View)
//    }
//
//    fun setOnItemClickListener(myClickListener: ItemClickListener) {
//        itemClickListener = myClickListener
//    }


    class WishlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView
        val textView1: MaterialTextView
        val textView2: MaterialTextView
        val addToCartBtn: MaterialButton
        val removeFromWishlistBtn: MaterialButton

        init {
            imageView = itemView.findViewById(R.id.imageView)
            textView1 = itemView.findViewById(R.id.tvName)
            textView2 = itemView.findViewById(R.id.tvPrice)
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn)
            removeFromWishlistBtn = itemView.findViewById(R.id.removeFromWishlistBtn)
        }
    }
}