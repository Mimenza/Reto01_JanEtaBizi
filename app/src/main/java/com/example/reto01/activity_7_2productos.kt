package com.example.reto01

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Adapter.ProductsRecyclerAdapter
import com.example.reto01.Adapter.UsersRecyclerAdapter
import com.example.reto01.Model.Producto
import com.example.reto01.Model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_1login.*
import kotlinx.android.synthetic.main.activity_7_1usuarios.*
import kotlinx.android.synthetic.main.activity_7_2productos.*

class activity_7_2productos : AppCompatActivity() {
    private val activity = this

    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var listProducts: MutableList<Producto>
    private lateinit var productsRecyclerAdapter: ProductsRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper

    private lateinit var context:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_2productos)
        getSupportActionBar()?.hide()
        initViews()
        initObjects()

    }



    private fun initViews(){
        recyclerViewProducts =recyclerViewProductos as RecyclerView

    }
    private fun initObjects(){
        listProducts = ArrayList()

        context =this
        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()


    }

     fun loadproducto(producto:Producto){

        val sharedPreferences = getSharedPreferences("product", 0)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()

        val productJson = gson.toJson(producto)

        sharedPreferencesEditor.putString("product", productJson)
        sharedPreferencesEditor.commit()

         val i = Intent(this, activity_7_2_2producto::class.java)
          startActivity(i)

    }


    /**
     * This class is to fetch all products records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<Producto>>() {

        override fun doInBackground(vararg p0: Void?): List<Producto> {
            return databaseHelper.getAllProducts()
        }

        override fun onPostExecute(result: List<Producto>?) {
            super.onPostExecute(result)
            listProducts.clear()
            listProducts.addAll(result!!)

            //Cargar el adapter después de llamar a la bbdd
            val adapter = ProductsRecyclerAdapter(listProducts, context)

            recyclerViewProducts.layoutManager = LinearLayoutManager(context)
            recyclerViewProducts.adapter = adapter

        }

    }


}