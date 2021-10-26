
package com.example.reto01.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.DatabaseHelper
import com.example.reto01.Model.Pedido_producto

import com.example.reto01.R
import kotlinx.android.synthetic.main.itemorderrecycler.view.*
import kotlinx.android.synthetic.main.itemorderrecycler.view.textViewNameProductPedido
import kotlin.properties.Delegates


class SubordersRecyclerAdapter(private val listSuborders: List<Pedido_producto>, contexto: Context) : RecyclerView.Adapter<SubordersRecyclerAdapter.SubordersViewHolder>(){

    val contexto = contexto
    private var id by Delegates.notNull<Int>()
    lateinit var databaseHelper:DatabaseHelper

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SubordersViewHolder {
        // inflating recycler item view
        initObjects()

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemsuborderrecycler, parent, false)
        return SubordersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubordersViewHolder, i: Int) {

        var suborder = listSuborders[i]

        holder.textViewName.text = (suborder.id_product?.let { databaseHelper.getProduct(it)?.name_product }).toString()
        holder.textViewAmount.text = suborder.quantity.toString()

    }

    override fun getItemCount(): Int {
        return listSuborders.size
    }


    /**
     * ViewHolder class
     */
    inner class SubordersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var  textViewName: TextView
        var  textViewAmount: TextView

        init {
            textViewName = view.textViewNameProductPedido
            textViewAmount = view.txtViewCantidad

        }
    }

    private fun initObjects() {
        databaseHelper = DatabaseHelper( contexto , "janEtaBizi", null, 1)

    }

}