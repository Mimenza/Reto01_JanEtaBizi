package com.example.reto01.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Model.Producto
import com.example.reto01.R
import kotlinx.android.synthetic.main.viewholder_cart.view.*

class MyCardsCartAdapter(val productos: ArrayList<Producto>, val context: Context): RecyclerView.Adapter<MyCardsCartAdapter.ViewHolder>(){


    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int, ): ViewHolder {
       val v = LayoutInflater.from(ViewGroup.context).inflate(R.layout.viewholder_cart, ViewGroup, false)

        return  ViewHolder(v)
    }

    override fun onBindViewHolder(ViewHolder: ViewHolder, i: Int) {
        val spinnerVal= intArrayOf(1,2,3,4,5,6,7,8,9,10)
        var item = productos[i]
        //var adaptador= ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item, spinnerVal)
        ViewHolder.itemImage.setImageResource(item.img)
        ViewHolder.itemTitle.text= item.name_product
        ViewHolder.itemPrecioProducto.text= item.price.toString()
        ViewHolder.itemCategoria.text=item.category
        //ViewHolder.itemSpiner.adapter=adaptador

    }

    override fun getItemCount(): Int {
        return  productos.size
    }

      inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

           var itemImage: ImageView
           var itemTitle: TextView
           var itemPrecioProducto : TextView
           var itemCategoria : TextView
           var itemSpiner: Spinner

          //Inicializar las variables
              init {
                  itemImage = itemView.imgv_cardimg
                  itemTitle = itemView.txtv_cardtittle
                  itemPrecioProducto = itemView.txtv_cardeurostotal
                  itemCategoria=itemView.txtv_cardcategory
                  itemSpiner = itemView.spinner_carrito
              }
      }

}




