package com.example.reto01.Adapter

import android.app.Activity
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
import com.example.reto01.activity_5carrito
import com.google.gson.Gson
import kotlinx.android.synthetic.main.viewholder_cart.view.*
import com.example.reto01.MainActivity





class ProductCartAdapter(private val productos: List<Producto>, val context: Context) :
    RecyclerView.Adapter<ProductCartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context)
            .inflate(R.layout.gproduct_card, ViewGroup, false)


        return ViewHolder(v)
    }

    override fun onBindViewHolder(ViewHolder: ViewHolder, i: Int) {
        var item = productos[i]

        ViewHolder.itemImage.setImageResource(item.img)
        ViewHolder.itemTitle.text = item.name_product
        ViewHolder.itemPrecioProducto.text = item.price.toString()
        ViewHolder.itemCategoria.text = item.category











        /*ViewHolder.itemSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected( parent: AdapterView<*>, view: View, position: Int, id: Long) {

                // Guardar datos del carrito en el shared preferences

                //Poner nombre al fichero
                val preferences = view.getContext().getSharedPreferences("carrito", 0)
                val editor : SharedPreferences.Editor= preferences.edit()

                //Crear un json y una clase para los items del carrito
                val gson = Gson()
                val item_Carrito = Carrito_item(item.id_product, parent.getItemAtPosition(position).toString().toInt(),item.price)
                val itemJson = gson.toJson(item_Carrito)
                val itemname = "item" + item.id_product

                //Subir datos
                editor.putString(itemname, itemJson.toString())
                editor.commit()

                //llamar funcion para poner el precio

                if (context is activity_5carrito) {
                    context.calcularTotal(item_Carrito)
                }


                //Borrar datos extra
                //preferences.edit().remove("item").commit();

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }*/
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrecioProducto: TextView
        var itemCategoria: TextView


        //Inicializar las variables
        init {
            itemImage = itemView.imgv_cardimg
            itemTitle = itemView.txtv_cardtittle
            itemPrecioProducto = itemView.txtv_cardeurostotal
            itemCategoria = itemView.txtv_cardcategory

        }
    }
}




