package com.example.reto01

<<<<<<< HEAD:app/src/main/java/com/example/reto01/activity_7_2_1producto.kt
=======
import android.content.Context
import android.os.AsyncTask
>>>>>>> 6e47b6a211e0ef46f115a4f3830ce7a09c08fa18:app/src/main/java/com/example/reto01/activity_7_2productos.kt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD:app/src/main/java/com/example/reto01/activity_7_2_1producto.kt
=======
import com.example.reto01.Adapter.ProductsRecyclerAdapter
>>>>>>> 6e47b6a211e0ef46f115a4f3830ce7a09c08fa18:app/src/main/java/com/example/reto01/activity_7_2productos.kt
import com.example.reto01.Adapter.UsersRecyclerAdapter
import com.example.reto01.Model.Producto
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

<<<<<<< HEAD:app/src/main/java/com/example/reto01/activity_7_2_1producto.kt
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
=======
    private fun initViews(){
        recyclerViewProducts =recyclerViewProductos as RecyclerView
>>>>>>> 6e47b6a211e0ef46f115a4f3830ce7a09c08fa18:app/src/main/java/com/example/reto01/activity_7_2productos.kt

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