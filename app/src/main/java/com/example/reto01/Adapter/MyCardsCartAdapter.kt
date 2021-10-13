package com.example.reto01.Adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.R
import kotlinx.android.synthetic.main.viewholder_cart.view.*

class MyCardsCartAdapter: RecyclerView.Adapter<MyCardsCartAdapter.ViewHolder>(){





    val titles= arrayOf("Title", "Title 2", "Title 3", "Title 4")
    val precioproducto= arrayOf("0€", "12€", "13€", "14€")
    val precioproductototal= arrayOf("0€", "120€", "130€", "140€")
    val images= intArrayOf(R.drawable.dessert, R.drawable.aceite3, R.drawable.fresa, R.drawable.blueberries)
    val minus= intArrayOf(R.drawable.ic_minus, R.drawable.ic_minus, R.drawable.ic_minus, R.drawable.ic_minus)
    val mas= intArrayOf(R.drawable.ic_plus, R.drawable.ic_plus, R.drawable.ic_plus, R.drawable.ic_plus)



    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
       val v = LayoutInflater.from(ViewGroup.context).inflate(R.layout.viewholder_cart, ViewGroup, false)

        return  ViewHolder(v)
    }

    override fun onBindViewHolder(ViewHolder: ViewHolder, i: Int) {

        ViewHolder.itemImage.setImageResource(images[i])
        ViewHolder.itemTitle.text= titles[i]
        ViewHolder.itemMinus.setImageResource(minus[i])
        ViewHolder.itemMas.setImageResource(mas[i])
        ViewHolder.itemPrecioProducto.text= precioproducto[i]
        ViewHolder.itemPrecioProductoTotal.text= precioproductototal[i]

    }

    override fun getItemCount(): Int {
        return  titles.size
    }


      inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

           var itemImage: ImageView
           var itemTitle: TextView
           var itemMinus : ImageView
           var itemMas : ImageView
           var itemPrecioProducto : TextView
           var itemPrecioProductoTotal : TextView

          //Inicializar las variables
              init {
                  itemImage = itemView.imgv_cardimg
                  itemMas = itemView.imgv_cardmas
                  itemMinus = itemView.imgv_cardmenos
                  itemTitle = itemView.txtv_cardtittle
                  itemPrecioProducto = itemView.txtv_cardeuros
                  itemPrecioProductoTotal = itemView.txtv_cardeurostotal

              }

      }



}




