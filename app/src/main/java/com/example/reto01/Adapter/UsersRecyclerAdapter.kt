package com.example.reto01.Adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Model.User
import com.example.reto01.R
import com.example.reto01.activity_7_1_1usuario
import kotlinx.android.synthetic.main.itemuserrecycler.view.*


class UsersRecyclerAdapter(private val listUsers: List<User>) : RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserViewHolder {
        // inflating recycler item view

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemuserrecycler, parent, false)
        return UserViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: UserViewHolder, i: Int) {

        var usuario = listUsers[i]
        println(usuario)
        holder.textViewImg.setImageResource(R.drawable.ic_person)
        holder.textViewName.text = usuario.name
        holder.textViewEmail.text = usuario.email
        holder.textViewSurname.text = usuario.password
        holder.itemView.setOnClickListener { onclick(usuario,holder.itemView.context ) }

    }

    private fun onclick(usuario: User, context: Context) {

        val intent = Intent( context, activity_7_1_1usuario::class.java)
        intent.putExtra("name",usuario.name)
        intent.putExtra("surname",usuario.surname)
        intent.putExtra("correo",usuario.email)
        intent.putExtra("pass",usuario.password)
        intent.putExtra("ciudad",usuario.city)
        intent.putExtra("cp",usuario.cp)
        intent.putExtra("direccion",usuario.address)
        intent.putExtra("tarjeta",usuario.num_tarjeta)
        intent.putExtra("tlfn",usuario.tlf)
        intent.putExtra("desc",usuario.description)
        //----------------------------------------------
        //enviamos todos los datos para futuro update

        intent.putExtra("id",usuario.id)
        intent.putExtra("admin",usuario.admin)
        intent.putExtra("caducidad",usuario.caducidad)
        intent.putExtra("ccv",usuario.ccv)

        context.startActivity(intent)
   }

    override fun getItemCount(): Int {
        return listUsers.size
    }


    /**
     * ViewHolder class
     */
    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewImg:ImageView
        var textViewName: TextView
        var textViewEmail: TextView
        var textViewSurname: TextView

        init {
            textViewImg = view.imageViewImg
            textViewName = view.textViewNameProductPedido
            textViewEmail = view.textViewEmail
            textViewSurname = view.textViewSurname
        }
    }
}

