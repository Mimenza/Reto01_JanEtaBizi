package com.example.reto01.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Model.User
import com.example.reto01.R


class UsersRecyclerAdapter(private val listUsers: List<User>,val context: Context) : RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder{
        // inflating recycler item view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemuserrecycler, parent, false)

        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textViewName.text = listUsers[position].name
        holder.textViewEmail.text = listUsers[position].email
        holder.textViewPassword.text = listUsers[position].password
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
        var textViewPassword: TextView

        init {
            textViewName = view.findViewById<View>(R.id.textViewName) as TextView
            textViewEmail = view.findViewById<View>(R.id.textViewEmail) as TextView
            textViewPassword = view.findViewById<View>(R.id.textViewSurname) as TextView
        }
    }


}