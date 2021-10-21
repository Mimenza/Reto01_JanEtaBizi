package com.example.reto01

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidtutorialshub.loginregisterkotlin.adapters.UsersRecyclerAdapter
import com.example.reto01.Model.User

class activity_7_0_1usuarios : AppCompatActivity() {


        private val activity = this


        private lateinit var recyclerViewUsers:RecyclerView
        private lateinit var listUsers: List<User>
        private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter
        private lateinit var databaseHelper: DatabaseHelper

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_7_1usuarios)
            supportActionBar!!.title = ""
            initViews()
            initObjects()
        }
        /**
         * This method is to initialize views
         */
        private fun initViews() {

            recyclerViewUsers = findViewById(R.id.recyclerViewUsers) as RecyclerView
        }
        /**
         * This method is to initialize objects to be used
         */
        private fun initObjects() {
            listUsers = ArrayList()
            usersRecyclerAdapter = UsersRecyclerAdapter(listUsers)
            val mLayoutManager = LinearLayoutManager(applicationContext)
            recyclerViewUsers.layoutManager = mLayoutManager
            recyclerViewUsers.itemAnimator = DefaultItemAnimator()
            recyclerViewUsers.setHasFixedSize(true)
            recyclerViewUsers.adapter = usersRecyclerAdapter
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

        }

    }
