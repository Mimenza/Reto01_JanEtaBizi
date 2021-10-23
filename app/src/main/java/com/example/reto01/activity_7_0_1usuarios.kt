package com.example.reto01

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reto01.Adapter.UsersRecyclerAdapter
import com.example.reto01.Model.User
import kotlinx.android.synthetic.main.activity_7_1usuarios.*

class activity_7_0_1usuarios : AppCompatActivity() {
    private val activity = this

    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var listUsers: MutableList<User>
    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7_1usuarios)
        getSupportActionBar()?.hide()
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initViews()
        initObjects()
    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {
        recyclerViewUsers =recyclerViewUsuarios as RecyclerView
    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        listUsers = ArrayList()

        val adapter = UsersRecyclerAdapter(listUsers, this)
        recyclerViewUsers.layoutManager = LinearLayoutManager(this)
        recyclerViewUsers.adapter = adapter

        databaseHelper = DatabaseHelper(activity, "janEtaBizi", null, 1)

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
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

        }

    }



}

