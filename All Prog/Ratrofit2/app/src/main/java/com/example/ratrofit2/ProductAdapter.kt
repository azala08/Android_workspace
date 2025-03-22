package com.example.ratrofit2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductAdapter(var context: Context, var productList : MutableList<ProductModel>) : RecyclerView.Adapter<productview>()
{
    var id = 0
    var apiinterface: Apiinterface = ApiClient().getapiclient().create(Apiinterface::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productview
    {
        var layout = LayoutInflater.from(parent.context)
        var layoutview = layout.inflate(R.layout.design,parent,false)
        return productview(layoutview)
    }

    override fun getItemCount(): Int
    {
        return productList.size
    }

    override fun onBindViewHolder(holder: productview, position: Int)
    {

        id = productList.get(position).id
        holder.txt1.setText(productList.get(position).product_name)
        holder.txt2.setText(productList.get(position).product_price)
        holder.txt3.setText(productList.get(position).product_description)

        holder.img1.setOnClickListener {

            var i = Intent(context,UpdateActivity::class.java)
            i.putExtra("pname",productList.get(position).product_name)
            i.putExtra("pprice",productList.get(position).product_price)
            i.putExtra("pdes",productList.get(position).product_description)
            i.putExtra("id",productList.get(position).id)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)

        }
        holder.img2.setOnClickListener {

            var call: Call<Void> = apiinterface.deletedata(productList.get(position).id)
            call.enqueue(object: Callback<Void>
            {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()

                    var i = Intent(context,MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(i)

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                    Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()

                }
            })
        }

    }

}

class productview(view : View) : RecyclerView.ViewHolder(view)
{
    var txt1: TextView = view.findViewById(R.id.txtpname)
    var txt2:TextView = view.findViewById(R.id.txtpprice)
    var txt3:TextView = view.findViewById(R.id.txtpdes)
    var img1: ImageView = view.findViewById(R.id.edit)
    var img2:ImageView = view.findViewById(R.id.delete)
}