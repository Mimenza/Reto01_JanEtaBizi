package com.example.reto01.Adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Model.Carrito_item
import com.example.reto01.Model.Producto
import com.example.reto01.R
import com.google.gson.Gson
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
        var adaptador = ArrayAdapter(context, android.R.layout.simple_spinner_item, arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        ViewHolder.itemImage.setImageResource(item.img)
        ViewHolder.itemTitle.text = item.name_product
        ViewHolder.itemPrecioProducto.text = item.price.toString()
        ViewHolder.itemCategoria.text = item.category
        ViewHolder.itemSpiner.adapter = adaptador
        ViewHolder.itemSpiner.setSelection(0)

        ViewHolder.itemSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected( parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val position1 = "cantidad : " + parent.getItemAtPosition(position).toString()
                val parentID = "producto ID : " + item.id_product
                val output = parentID  + " " + position1
                 Toast.makeText(context,output, Toast.LENGTH_LONG).show()

                val preferences = view.getContext().getSharedPreferences("carrito", 0)
                val editor : SharedPreferences.Editor= preferences.edit()
                val gson = Gson()
                val item_Carrito = Carrito_item(item.id_product, parent.getItemAtPosition(position).toString())
                val itemJson = gson.toJson(item_Carrito)
                editor.putString("item", itemJson)
                editor.commit()



            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
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
}




