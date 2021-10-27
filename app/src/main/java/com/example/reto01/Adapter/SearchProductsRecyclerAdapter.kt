package com.example.recycleviewexample.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Model.Producto
import kotlinx.android.synthetic.main.gproduct_card.view.*


class SearchProductsRecyclerAdapter(itemList:ArrayList<Producto>) :
    RecyclerView.Adapter<SearchProductsRecyclerAdapter.MyViewHolder>(),
    Filterable {
    private val dataSet:ArrayList<Producto>
    private val FullList :ArrayList<Producto>


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textViewImg: ImageView
        var textViewTitle: TextView
        var textViewCategory: TextView


        init {

            textViewImg = view.imgv_cardimg
            textViewTitle = view.txtv_cardtittle
            textViewCategory = view.txtv_cardcategory


        }
    }

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):MyViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(
            com.example.reto01.R.layout.activity_7_2productos,
            parent, false
        )

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position:Int) {
        val currentItem:Producto = dataSet[position]
        holder.textViewImg.setImageResource(currentItem.img)
        holder.textViewTitle.setText(currentItem.name_product)
        holder.textViewCategory.setText(currentItem.category)


    }

    override fun getItemCount():Int {
        return dataSet.size
    }

    override fun getFilter():Filter {
        return Searched_Filter
    }
    init {
        dataSet = itemList
        FullList = ArrayList<Producto>(itemList)
    }
    private val Searched_Filter:Filter = object : Filter() {
        override fun performFiltering(constraint:CharSequence):FilterResults {
            val filteredList:ArrayList<Producto> = ArrayList<Producto>()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(FullList)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in FullList) {
                    if (item.name_product?.toLowerCase()?.contains(filterPattern) == true) {
                        filteredList.add(item)
                    }
                    if (item.category?.toLowerCase()?.contains(filterPattern) == true) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint:CharSequence, results:FilterResults) {
            dataSet.clear()
            dataSet.addAll((results.values as ArrayList<Producto>))
            notifyDataSetChanged()
        }
    }


}