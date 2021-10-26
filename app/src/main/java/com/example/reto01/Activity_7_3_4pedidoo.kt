package com.example.reto01

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Adapter.SubordersRecyclerAdapter
import com.example.reto01.Model.Pedido_producto
import kotlinx.android.synthetic.main.activity_7_3_4pedido.*
import kotlinx.android.synthetic.main.activity_7_3_4pedido.recyclerViewPedidosProductos

class activity_7_3_4pedidoo: AppCompatActivity() {
    private val activity = this
    private lateinit var recyclerViewSuborders: RecyclerView
    private lateinit var listSuborders : MutableList<Pedido_producto>
    private lateinit var subordersRecyclerAdapter: SubordersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private var id_OrderLate:Int = 0
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_3_4pedido)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initViews()
        initObjects()

        //Recogemos el id del order del intent
         var id_Order =intent.getIntExtra("idOrder",0).toString()
         id_OrderLate=id_Order.toInt()

        txt_id_order.setText("Pedido numero: "+id_Order)
    }

    private fun initViews() {
        recyclerViewSuborders = recyclerViewPedidosProductos as RecyclerView
    }

    private fun initObjects() {
        listSuborders = ArrayList()

        context =this;
        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<Pedido_producto>>() {

        override fun doInBackground(vararg p0: Void?): List<Pedido_producto>? {

            return databaseHelper.getPedidoProducto1(1)

        }

        override fun onPostExecute(result: List<Pedido_producto>?) {
            super.onPostExecute(result)
            listSuborders.clear()
            listSuborders.addAll(result!!)

            println("ESTO ES UN PRINT" + listSuborders)
            val adapter = SubordersRecyclerAdapter(listSuborders, context)
            recyclerViewSuborders.layoutManager = LinearLayoutManager(context)
            recyclerViewSuborders.adapter = adapter
        }

    }



}
