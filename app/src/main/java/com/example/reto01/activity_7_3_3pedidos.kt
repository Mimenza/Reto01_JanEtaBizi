package com.example.reto01

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Adapter.OrdersRecyclerAdapter
import com.example.reto01.Model.Order
import kotlinx.android.synthetic.main.activity_7_3pedidos.*

class activity_7_3_3pedidos : AppCompatActivity() {
    private val activity = this
    private lateinit var recyclerViewOrders: RecyclerView
    private lateinit var listOrders : MutableList<Order>
    private lateinit var ordersRecyclerAdapter: OrdersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper
    lateinit var context: Context

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(0,0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_3pedidos)
        getSupportActionBar()?.hide()

        initViews()
        initObjects()

        imgv_7_3atras.setOnClickListener() {
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
        }
    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {
        recyclerViewOrders =recyclerViewPedidos as RecyclerView
    }

    /**
     * This method is to initialize objects to be used
     */


    private fun initObjects() {
        listOrders = ArrayList()

        context =this;
        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<Order>>() {

        override fun doInBackground(vararg p0: Void?): List<Order> {
            return databaseHelper.getAllOrders()
        }

        override fun onPostExecute(result: List<Order>?) {
            super.onPostExecute(result)
            listOrders.clear()
            listOrders.addAll(result!!)

            println(listOrders)
            val adapter = OrdersRecyclerAdapter(listOrders, context)

            recyclerViewOrders.layoutManager = LinearLayoutManager(context)
            recyclerViewOrders.adapter = adapter
        }

    }



}
