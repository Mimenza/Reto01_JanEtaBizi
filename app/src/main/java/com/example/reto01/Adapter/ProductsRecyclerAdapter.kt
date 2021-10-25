package com.example.reto01.Adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Model.Producto
import com.example.reto01.Model.User
import com.example.reto01.R
import com.example.reto01.activity_7_1_1usuario
import com.example.reto01.activity_7_2_2producto
import com.google.gson.Gson
import kotlinx.android.synthetic.main.gproduct_card.view.*


class ProductsRecyclerAdapter(private val listProducts: List<Producto>) : RecyclerView.Adapter<ProductsRecyclerAdapter.ProductViewHolder>(){


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
        holder.textViewTitle.setText(producto.name_product)
        holder.textViewCategory.setText(producto.category)
        holder.textViewTotal.setText(producto.price.toString()+euro)
        holder.itemView.setOnClickListener { onclick(producto,holder.itemView.context ) }

    }

    private fun onclick(producto: Producto, context: Context) {
         //Al hacer intent pasamos estos parámetros

        val intent = Intent( context, activity_7_2_2producto::class.java)
        intent.putExtra("title",producto.name_product)
        //intent.putExtra("description",producto.)
        intent.putExtra("img",producto.img.toString())
        intent.putExtra("price",producto.price )
        //----------------------------------------------
        //enviamos todos los datos para futuro update

        context.startActivity(intent)
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


