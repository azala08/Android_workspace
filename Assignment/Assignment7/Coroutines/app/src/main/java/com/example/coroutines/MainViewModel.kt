package com.example.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel()
{
    private val users1 = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = users1

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun fetchUsers() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = RetrofitInstance.api.getUsers()
                users1.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
            _loading.value = false
        }
    }
}