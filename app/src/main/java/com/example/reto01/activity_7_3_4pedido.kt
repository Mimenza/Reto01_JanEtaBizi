package com.example.reto01

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Adapter.SubordersRecyclerAdapter
import com.example.reto01.Model.Pedido_producto
import kotlinx.android.synthetic.main.activity_7_1_1usuario.*
import kotlinx.android.synthetic.main.activity_7_3_4pedido.*
import kotlinx.android.synthetic.main.activity_7_3_4pedido.recyclerViewPedidosProductos

class activity_7_3_4pedido: AppCompatActivity() {
    private val activity = this
    private lateinit var recyclerViewSuborders: RecyclerView
    private lateinit var listSuborders : MutableList<Pedido_producto>
    private  var listSuborders1 : MutableList<Pedido_producto> = arrayListOf()
    private lateinit var subordersRecyclerAdapter: SubordersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper
    lateinit var context: Context
    var id_OrderLate:Int = 0

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(0,0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_3_4pedido)
        getSupportActionBar()?.hide()

        initViews()
        initObjects()

        //Recogemos el id del order del intent para ponerlo en la cabecera
         var id_Order =intent.getIntExtra("idOrder",0)

        id_OrderLate = id_Order

        txt_id_order.setText("Pedido numero: "+id_Order).toString()

        imgv_7_3_4atras.setOnClickListener() {
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
            this.overridePendingTransition(0,0)
        }
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

            println("ID ORDER LATE " + id_OrderLate)
            return databaseHelper.getPedidoProducto1(id_OrderLate)

        }

        override fun onPostExecute(result: List<Pedido_producto>?) {
            super.onPostExecute(result)
            listSuborders.clear()
            listSuborders.addAll(result!!)
            var adapter = SubordersRecyclerAdapter(listSuborders1, context)

             adapter = SubordersRecyclerAdapter(listSuborders, context)
            recyclerViewSuborders.layoutManager = LinearLayoutManager(context)
            recyclerViewSuborders.adapter = adapter
        }

    }



}
