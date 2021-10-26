
package com.example.reto01.Adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.DatabaseHelper
import com.example.reto01.Model.Order

import com.example.reto01.R
import com.example.reto01.activity_7_3_4pedidoo
import kotlinx.android.synthetic.main.itemorderrecycler.view.*
import kotlinx.android.synthetic.main.itemorderrecycler.view.textViewNameProductPedido
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.properties.Delegates


class OrdersRecyclerAdapter(private val listOrders: List<Order>, contexto: Context) : RecyclerView.Adapter<OrdersRecyclerAdapter.OrdersViewHolder>(){

    val contexto = contexto
    private var id by Delegates.notNull<Int>()
    lateinit var databaseHelper:DatabaseHelper

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): OrdersViewHolder {
        // inflating recycler item view
        initObjects()

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemorderrecycler, parent, false)
        return OrdersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, i: Int) {

        var order = listOrders[i]
        holder.textViewName.text = ((order.id_user?.let { databaseHelper.getUserId(it) })?.name).toString()





        holder.textViewOrder.text= order.id_order.toString()
        holder.textViewPrecio.text= order.total.toString()
        holder.itemView.setOnClickListener { onclick(order,holder.itemView.context )}

    }

    private fun onclick(order: Order, context: Context) {

       val intent = Intent( context, activity_7_3_4pedidoo::class.java)
        intent.putExtra("idOrder",order.id_order)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return listOrders.size
    }


    /**
     * ViewHolder class
     */
    inner class OrdersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewOrder: TextView
        var  textViewName: TextView
        var  textViewDate: TextView
        var textViewPrecio:TextView

        init {
            textViewOrder = view.txtViewOrder
            textViewName = view.textViewNameProductPedido
            textViewDate = view.txtViewDate
            textViewPrecio= view.txtViewCantidad
        }
    }

    private fun initObjects() {
        databaseHelper = DatabaseHelper( contexto , "janEtaBizi", null, 1)

    }

}