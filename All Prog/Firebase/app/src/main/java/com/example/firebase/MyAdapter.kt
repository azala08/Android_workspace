package com.example.firebase

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.ViewHolder

class MyAdapter(context: Context, options: FirebaseRecyclerOptions<Model>) : FirebaseRecyclerAdapter<Model, MyView>(options)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView
    {
        var inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.design,parent,false)
        return  MyView(inflater)
    }
    override fun onBindViewHolder(holder: MyView, position: Int, model: Model)
    {
        holder.name.setText(model.name)
        holder.num.setText(model.number)
        holder.email.setText(model.email)

        holder.edit.setOnClickListener {
            var dialogPlus = DialogPlus.newDialog(holder.edit.context)
                .setContentHolder(ViewHolder(R.layout.dialogcontent))
                .setExpanded(true, 1100)
                .create()

            val myview = dialogPlus.holderView

            val name = myview.findViewById<EditText>(R.id.edtname)
            val num = myview.findViewById<EditText>(R.id.edtnum)
            val email = myview.findViewById<EditText>(R.id.edtemail)
            val update = myview.findViewById<Button>(R.id.btnupdate)

           name.setText(model.name)
           num.setText(model.number)
           email.setText(model.email)


            update.setOnClickListener {
                var map = HashMap<String,Any>()
                map["name"] = name.text.toString()
                map["number"] = num.text.toString()
                map["email"] = email.text.toString()

                FirebaseDatabase.getInstance().getReference().child("topstech")
                    .child(getRef(position).getKey()!!)
                    .updateChildren(map)
                    .addOnSuccessListener {
                        Toast.makeText(holder.edit.context,"Updated",Toast.LENGTH_LONG).show()

                        dialogPlus.dismiss()
                    }
                    .addOnFailureListener{
                        Toast.makeText(holder.edit.context,"Error",Toast.LENGTH_LONG).show()

                    }

        }
            dialogPlus.show()

        holder.delete.setOnClickListener{
            var alert = AlertDialog.Builder(holder.delete.context)
            alert.setTitle("DELETE?")
            alert.setPositiveButton("YES",{ dialogInterface: DialogInterface, i: Int ->

                FirebaseDatabase.getInstance().getReference().child("topstech")
                    .child(getRef(position).key!!)
                    .removeValue()

                notifyItemRangeChanged(position,0)

            })
            alert.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.cancel()
            })
            alert.show()
            }

        }

    }
}
class MyView(myview: View) : RecyclerView.ViewHolder(myview)
{
    var name: TextView
    var num:TextView
    var email:TextView
    var edit: ImageView
    var delete: ImageView

    init
    {
        name = itemView.findViewById(R.id.txt1)
        email = itemView.findViewById(R.id.txt2)
        num = itemView.findViewById(R.id.txt3)
        edit = itemView.findViewById(R.id.imgupdate)
        delete = itemView.findViewById(R.id.imgdelete)

    }
}
