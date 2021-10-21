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
import kotlinx.android.synthetic.main.viewholder_cart.view.*


class MyCardsCartAdapter(private val productos: List<Producto> , val context: Context) :
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


        //Recoger datos de Shared Preferences
        val prefs: SharedPreferences = context.getSharedPreferences("carrito", 0)
        val carrito = prefs.getString("item"+ item.id_product.toString(),null)

        //Parsear datos a objeto carrito_item
        val gsonFile = Gson()
        val carritoJson: Carrito_item = gsonFile.fromJson(carrito, Carrito_item::class.java)
        val cantidad = carritoJson.cantidad

        //Poner cantidad
         if (cantidad != null) {
            ViewHolder.itemSpiner.setSelection(cantidad-1)
        }


        ViewHolder.itemSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected( parent: AdapterView<*>, view: View, position: Int, id: Long) {

                // Guardar datos del carrito en el shared preferences

                //Poner nombre al fichero
                val preferences = view.getContext().getSharedPreferences("carrito", 0)
                val editor : SharedPreferences.Editor= preferences.edit()

                //Crear un json y una clase para los items del carrito
                val gson = Gson()
                val item_Carrito = Carrito_item(item.id_product, parent.getItemAtPosition(position).toString().toInt())
                val itemJson = gson.toJson(item_Carrito)
                val itemname = "item" + item.id_product

                //Subir datos
                editor.putString(itemname, itemJson.toString())
                editor.commit()

                //Borrar datos extra
                //preferences.edit().remove("item").commit();

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




