package com.example.reto01.Adapter


import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.*
import com.example.reto01.Model.Producto
import kotlinx.android.synthetic.main.gproduct_card.view.*


class ProductsRecyclerAdapter(private val listProducts: List<Producto>, val context: Context) : RecyclerView.Adapter<ProductsRecyclerAdapter.ProductViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ProductViewHolder {
        // inflating recycler item view

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.gproduct_card, parent, false)
        return ProductViewHolder(itemView)

    }


    override fun onBindViewHolder(holder: ProductViewHolder, i: Int) {

        //All products
        var producto= listProducts[i]
        var euro="€"
        holder.textViewImg.setImageResource(producto.img)

        holder.textViewTitle.text = context.getResources().getString(producto.name_product!!.toInt())
        holder.textViewCategory.setText(producto.category)
        holder.textViewTotal.setText(producto.price.toString()+euro)
        holder.itemView.setOnClickListener { onclick(producto,holder.itemView.context ) }

    }

    private fun onclick(producto: Producto, context: Context) {
         //Al hacer intent pasamos estos parámetros

        if(this.context is activity_7_2productos){

            (this.context).loadproducto(producto)
            (context as Activity).overridePendingTransition(0,0)
        }


    }

    override fun getItemCount(): Int {
        return listProducts.size
    }


    /**
     * ViewHolder class
     */
    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewTitle:TextView
        var textViewCategory: TextView
        var textViewTotal: TextView
        var textViewImg: ImageView

        init {
            textViewTitle = view.txtv_cardtittle
            textViewCategory = view.txtv_cardcategory
            textViewTotal = view.txtv_cardeurostotal
            textViewImg = view.imgv_cardimg
        }
    }


}


