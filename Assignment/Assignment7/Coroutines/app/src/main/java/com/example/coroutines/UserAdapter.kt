package com.example.coroutines

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.userName)
        val email: TextView = view.findViewById(R.id.userEmail)
        val profileImage: ImageView = view.findViewById(R.id.userImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.name.text = "${user.name.first} ${user.name.last}"
        holder.email.text = user.email
        Glide.with(holder.itemView.context).load(user.picture.large).into(holder.profileImage)
    }

    override fun getItemCount() = users.size
}