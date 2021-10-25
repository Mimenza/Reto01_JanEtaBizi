package com.example.reto01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Adapter.UsersRecyclerAdapter
import com.example.reto01.Model.Producto
import kotlinx.android.synthetic.main.activity_7_1usuarios.*

class activity_7_2_1producto : AppCompatActivity() {
    private val activity = this

    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var listProduct: MutableList<Producto>
    private lateinit var ProductCartAdapter: UsersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_2_1producto)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initViews()
        //initObjects()

    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {
        recyclerViewUsers = recyclerViewPedidos as RecyclerView
    }
}

    /**
     * This method is to initialize objects to be used
     */
   /* private fun initObjects() {
        listProduct = ArrayList()

        val adapter = ProductCartAdapter(listProduct, this)
        recyclerViewUsers.layoutManager = LinearLayoutManager(this)
        recyclerViewUsers.adapter = adapter

        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<Producto>>() {

        override fun doInBackground(vararg p0: Void?): List<Producto> {
            return databaseHelper.getAllUser()
        }

        override fun onPostExecute(result: List<Producto>?) {
            super.onPostExecute(result)
            listProduct.clear()
            listProduct.addAll(result!!)
        }

    }*/



