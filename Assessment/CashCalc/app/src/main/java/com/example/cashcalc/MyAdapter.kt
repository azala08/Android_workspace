package com.example.cashcalc

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
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
import java.util.Date
import java.util.Locale

class MyAdapter(option: FirebaseRecyclerOptions<Model>) :
    FirebaseRecyclerAdapter<Model, Myview>(option) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.design, parent, false)
        return Myview(inflater)
    }

    override fun onBindViewHolder(holder: Myview, position: Int, model: Model) {
        holder.desc.text = model.desc
        holder.total.text = model.total
        holder.datetime.text = model.datetime

        holder.updatebtn.setOnClickListener {
            showUpdateDialog(holder.itemView, position, model)

        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showUpdateDialog(view: View, position: Int, model: Model) {
        val inflater = LayoutInflater.from(view.context)
        val dialogView = inflater.inflate(R.layout.update_dialog, null)

        val dialog = AlertDialog.Builder(view.context)
            .setView(dialogView)
            .setCancelable(true)
            .create()
        dialog.show()

        val tvDesc = dialogView.findViewById<TextView>(R.id.tvDesc)
        val tvTotal = dialogView.findViewById<TextView>(R.id.tvTotal)
        val tvNotesTotal = dialogView.findViewById<TextView>(R.id.tvNotesTotal)
        val tvDateTime = dialogView.findViewById<TextView>(R.id.tvDateTime)
        val btnShare = dialogView.findViewById<ImageView>(R.id.buttonShare)
        val btnUpdate = dialogView.findViewById<ImageView>(R.id.btnedit)
        val btnDelete = dialogView.findViewById<ImageView>(R.id.btnDelete)

        tvDesc.text = model.desc
        tvTotal.text = model.total
        tvNotesTotal.text = model.notestotal
        tvDateTime.text = model.datetime

        btnUpdate.setOnClickListener {
            val editDialogView = inflater.inflate(R.layout.dialog_edit, null)

            val editDesc = editDialogView.findViewById<EditText>(R.id.editDesc)
            val editTotal = editDialogView.findViewById<EditText>(R.id.editTotal)
            val btnSave = editDialogView.findViewById<Button>(R.id.btnSave)

            editDesc.setText(tvDesc.text.toString())
            editTotal.setText(tvTotal.text.toString())

            val editDialog = AlertDialog.Builder(view.context)
                .setView(editDialogView)
                .setCancelable(true)
                .create()
            editDialog.show()

            btnSave.setOnClickListener {
                val updatedDesc = editDesc.text.toString()
                val updatedTotal = editTotal.text.toString()
                val currentTime = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault()).format(
                    Date()
                )

                val updatedData = mapOf(
                    "desc" to updatedDesc,
                    "total" to updatedTotal,
                    "datetime" to currentTime
                )

                getRef(position).updateChildren(updatedData)
                    .addOnSuccessListener {
                        Toast.makeText(view.context, "Updated Successfully", Toast.LENGTH_SHORT).show()

                        // Update original dialog UI with new values
                        tvDesc.text = updatedDesc
                        tvTotal.text = updatedTotal
                        tvDateTime.text = currentTime

                        editDialog.dismiss() // Close the edit dialog
                    }
                    .addOnFailureListener {
                        Toast.makeText(view.context, "Update Failed", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        btnDelete.setOnClickListener {

            AlertDialog.Builder(view.context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes") { _, _ -> getRef(position).removeValue()

                    Toast.makeText(view.context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("No", null)
                .show()
        }

        btnShare.setOnClickListener {
            Toast.makeText(view.context, "Share feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}

class Myview(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var desc: TextView = itemView.findViewById(R.id.txtdesc)
    var total: TextView = itemView.findViewById(R.id.txtTotal_rs)
    var datetime: TextView = itemView.findViewById(R.id.txtDateTime)
    var updatebtn: ImageView = itemView.findViewById(R.id.updatebtn)
}