package com.example.reto01.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Model.User
import com.example.reto01.R

class UsersRecyclerAdapter(private val listUsers: List<User>) : RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // inflating recycler item view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemuserrecycler, parent, false)
        return UserViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textViewName.text = listUsers[position].name
        holder.textViewEmail.text = listUsers[position].email
        holder.textViewSurname.text = listUsers[position].password
    }
    override fun getItemCount(): Int {
        return listUsers.size
    }
    /**
     * ViewHolder class
     */
    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewName: TextView
        var textViewEmail: TextView
        var textViewSurname: TextView
        init {
            textViewName = view.findViewById(R.id.textViewName) as TextView
            textViewEmail = view.findViewById(R.id.textViewEmail) as TextView
            textViewSurname = view.findViewById(R.id.textViewSurname) as TextView
        }
    }
}