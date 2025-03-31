package com.example.coroutines

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity :AppCompatActivity()
{
    private val viewModel: MainViewModel by viewModels()
    lateinit var adapter: UserAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        adapter = UserAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.users.collectLatest { users ->
                adapter = UserAdapter(users)
                recyclerView.adapter = adapter
            }
        }

        lifecycleScope.launch {
            viewModel.loading.collectLatest { isLoading ->
                swipeRefreshLayout.isRefreshing = isLoading
            }
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchUsers()
        }

        viewModel.fetchUsers()
    }
}