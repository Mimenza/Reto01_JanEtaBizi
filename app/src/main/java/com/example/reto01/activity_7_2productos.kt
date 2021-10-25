package com.example.reto01

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Adapter.ProductsRecyclerAdapter
import com.example.reto01.Adapter.UsersRecyclerAdapter
import com.example.reto01.Model.Producto
import com.example.reto01.Model.User
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
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
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
            val adapter = ProductsRecyclerAdapter(listProducts)
            recyclerViewProducts.layoutManager = LinearLayoutManager(context)
            recyclerViewProducts.adapter = adapter

        }

    }


}