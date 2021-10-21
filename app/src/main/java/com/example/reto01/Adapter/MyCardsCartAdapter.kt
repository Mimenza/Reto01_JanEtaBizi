package com.example.reto01.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Model.Producto
import com.example.reto01.R
import kotlinx.android.synthetic.main.viewholder_cart.*
import kotlinx.android.synthetic.main.viewholder_cart.view.*

class MyCardsCartAdapter(private val productos: List<Producto>, val context: Context) :
    RecyclerView.Adapter<MyCardsCartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context)
            .inflate(R.layout.viewholder_cart, ViewGroup, false)
        return ViewHolder(v)


    }

    override fun onBindViewHolder(ViewHolder: ViewHolder, i: Int) {
        var item = productos[i]
        var adaptador = ArrayAdapter(
            context, android.R.layout.simple_spinner_item, arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        )
        ViewHolder.itemImage.setImageResource(item.img)
        ViewHolder.itemTitle.text = item.name_product
        ViewHolder.itemPrecioProducto.text = item.price.toString()
        ViewHolder.itemCategoria.text = item.category
        ViewHolder.itemSpiner.adapter = adaptador

        ViewHolder.itemSpiner.setSelection(0)

    }



    override fun getItemCount(): Int {
        return productos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrecioProducto: TextView
        var itemCategoria: TextView
        var itemSpiner: Spinner

        //Inicializar las variables
        init {
            itemImage = itemView.imgv_cardimg
            itemTitle = itemView.txtv_cardtittle
            itemPrecioProducto = itemView.txtv_cardeurostotal
            itemCategoria = itemView.txtv_cardcategory
            itemSpiner = itemView.spinner_carrito
        }
    }

   /* override fun onItemSelected(
        parent: AdapterView<*>,
        view: View,
        position: Int,
        id: Long
    ) {
        val position1 = parent.getItemAtPosition(position).toString()
        val parentID = parent.getSelectedItemId()
        val output = position + " " + parentID
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, output, duration)
        toast.show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}*/
}




