package com.example.reto01.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Model.Producto
import com.example.reto01.R
import com.example.reto01.activity_3principal
import kotlinx.android.synthetic.main.item_product.view.*


class ProductsCardsAdapter(private val listProducts: List<Producto>) : RecyclerView.Adapter<ProductsCardsAdapter.ProductViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ProductViewHolder {
        // inflating recycler item view

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, i: Int) {

        //All products
        var producto= listProducts[i]
        var euro="€"
        holder.textViewImg.setImageResource(producto.img)
        holder.textViewName.setText(producto.name_product)
        holder.textViewPrice.setText(producto.price.toString()+euro)


       holder.itemView.setOnClickListener { click1(producto,holder.itemView.context ) }
        holder.addProduct.setOnClickListener { click2(producto,holder.itemView.context ) }

    }

    private fun click1(producto: Producto, context: Context) {
        //Al hacer intent pasamos estos parámetros

        if (context is activity_3principal) {
            (context).recogerdatosProducto(producto)
        }
    }
    private fun click2(producto: Producto, context: Context) {
        //Al hacer intent pasamos estos parámetros

        if (context is activity_3principal) {
            (context).añadiralcarrito(producto)
        }
    }




    override fun getItemCount(): Int {
        return listProducts.size
    }


    /**
     * ViewHolder class
     */
    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewName:TextView
        var textViewPrice: TextView
        var textViewImg: ImageView
        var addProduct: Button

        init {
            textViewName = view.txtv_productname
            textViewImg = view.img_product
            textViewPrice = view.txtv_productprice
            addProduct = view.btn_addproduct

        }
    }


}


