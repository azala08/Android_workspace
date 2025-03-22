package com.example.cashcalculator

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cashcalculator.databinding.ActivityMain2Binding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class MainActivity2 : AppCompatActivity()
{
    lateinit var binding: ActivityMain2Binding
    lateinit var myAdapter: MyAdapter
    var isSearchVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= 21) {
            window.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                statusBarColor = ContextCompat.getColor(this@MainActivity2, R.color.black)
            }

        }

        setSupportActionBar(binding.tool2)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val searchText = binding.searchview.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchText.setTextColor(Color.BLACK)
        searchText.setHintTextColor(Color.GRAY)
        searchText.hint="search.."

        binding.home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.recycler.layoutManager = LinearLayoutManager(this)

        val options = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("RecordDetail"), Model::class.java)
            .build()

        myAdapter = MyAdapter(options)
        binding.recycler.adapter = myAdapter

        binding.fab.setOnClickListener {
            if (isSearchVisible) {
                binding.searchview.visibility = View.GONE
                binding.fab.show()
                isSearchVisible = false
            } else {
                binding.searchview.visibility = View.VISIBLE
                binding.fab.hide()
                isSearchVisible = true
            }
        }

        binding.searchview.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchFirebaseData(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchFirebaseData(it) }
                return false
            }
        })
    }

    private fun searchFirebaseData(query: String) {
        val searchQuery = FirebaseDatabase.getInstance().reference.child("RecordDetail")
            .orderByChild("desc")
            .startAt(query)
            .endAt(query + "\uf8ff")

        val options = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(searchQuery, Model::class.java)
            .build()

        myAdapter.updateOptions(options)
        myAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        myAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        myAdapter.stopListening()
    }

    override fun onBackPressed() {
        if (isSearchVisible) {
            binding.searchview.visibility = View.GONE
            binding.fab.show()
            isSearchVisible = false
        }
        else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.options,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {

            R.id.logout->
            {
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}