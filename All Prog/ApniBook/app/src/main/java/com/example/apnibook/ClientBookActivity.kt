package com.example.apnibook

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ClientBookActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var clientAdapter: ClientAdapter
    private lateinit var fab: FloatingActionButton


    lateinit var addname : EditText
    lateinit var addimg : ImageView
    lateinit var addproductBtn : Button
    private var filepath: Uri? = null
    lateinit var bitmap: Bitmap




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_book)

        // Initialize RecyclerView
//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Create client items
//        val clientItems = listOf(
//            ClientItem(R.drawable.a1, "a", "222222222"),
//            ClientItem(R.drawable.b1, "brijesh", "1231231231"),
//            ClientItem(R.drawable.a2, "par", "222222222"),
//            ClientItem(R.drawable.b2, "mitesh", "7359526453"),
//            ClientItem(R.drawable.login, "jay", "8780846464"),
//            ClientItem(R.drawable.a1, "kruti", "8849817263"),
//            ClientItem(R.drawable.a2, "suhan", "8888888822"),
//            ClientItem(R.drawable.b1, "dummy", "8888888883"),
//            ClientItem(R.drawable.b2, "xyz", "8888888888")
//        )
//
//        // Initialize adapter
//        clientAdapter = ClientAdapter(clientItems) { position ->
//            // Handle client item click
//        }
//
//        recyclerView.adapter = clientAdapter
//
//        // Initialize FAB
//        fab = findViewById(R.id.fab)
//        fab.setOnClickListener {
//            // Handle add new client
//        }


    }
}