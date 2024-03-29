package com.example.reto01

import android.content.Context
import android.content.Intent
import android.content.LocusId
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Adapter.UsersRecyclerAdapter
import com.example.reto01.Model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_7_1_1usuario.*
import kotlinx.android.synthetic.main.activity_7_1usuarios.*

class activity_7_1usuarios : AppCompatActivity() {
    private val activity = this
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var listUsers: MutableList<User>
    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(0,0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_1usuarios)
        getSupportActionBar()?.hide()
        initViews()
        initObjects()

        imgv_7_1atras.setOnClickListener() {
            val i = Intent(this, activity_7admin::class.java)
            startActivity(i)
            this.overridePendingTransition(0,0)
        }
    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {
        recyclerViewUsers =recyclerViewPedidos as RecyclerView
    }

    /**
     * This method is to initialize objects to be used
     */
    lateinit var context:Context

    private fun initObjects() {
        listUsers = ArrayList()

        context =this;
        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    fun deleteUser(user:User){

        val sharedPreferences = getSharedPreferences("user", 0)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()

        val productJson = gson.toJson(user)

        sharedPreferencesEditor.putString("user", productJson)
        sharedPreferencesEditor.commit()

    }

    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<User>>() {

        override fun doInBackground(vararg p0: Void?): List<User> {
            return databaseHelper.getAllUser()
        }

        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)


            val adapter = UsersRecyclerAdapter(listUsers)
            recyclerViewUsers.layoutManager = LinearLayoutManager(context)
            recyclerViewUsers.adapter = adapter
        }

    }


}

