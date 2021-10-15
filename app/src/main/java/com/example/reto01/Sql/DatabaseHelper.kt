package com.example.reto01
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.reto01.Model.User


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "reto1.db"
        // User table name
        private const val TBL_USER = "User"

        // User Table Columns names
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"
        private val COLUMN_USER_ADMIN = "user_admin"
    }



    // create table sql query
    private  val CREATE_USER_TABLE =
        "CREATE TABLE ${TBL_USER} (" +
                "${COLUMN_USER_ID} INTEGER PRIMARY KEY," +
                "${COLUMN_USER_NAME} TEXT," +
                "${COLUMN_USER_EMAIL} TEXT," +
                "${COLUMN_USER_PASSWORD} TEXT," +
                "${COLUMN_USER_ADMIN} INTEGER)"


    // drop table sql query
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS ${TBL_USER}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    //Insertar usuario
    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        values.put(COLUMN_USER_ADMIN,  user.admin)
        // Inserting Row
        db.insert(TBL_USER, null, values)
        db.close()
    }

    //Actualizar usuario

    fun updateUser(user: User){

        // Gets the data repository in write mode
        val db = this.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put("Id", COLUMN_USER_ID)
            put("Email", COLUMN_USER_NAME)
            put("Email", COLUMN_USER_EMAIL)
            put("Password", COLUMN_USER_PASSWORD)
            put("Admin", COLUMN_USER_ADMIN)

        }
        // update según el id de usuario
        db.update(
            TBL_USER, values, "${COLUMN_USER_ID} = ?",
            arrayOf(user.id.toString()))
        db.close()
    }

    //Eliminar usuario
    fun deleteUser(user: User) {
        val db = this.writableDatabase
        // Eliminar usuario según id
        db.delete(
            TBL_USER, "${COLUMN_USER_ID}= ?",
            arrayOf(user.id.toString()))
        db.close()

    }

    //Lista que hace return de todos los usuarios
    fun getAllUser() {
        val db = this.readableDatabase

        val list = arrayOf(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_EMAIL, COLUMN_USER_PASSWORD, COLUMN_USER_ADMIN)

        // Ordenar por ID ascendente
        val sortOrder = "${COLUMN_USER_ID} ASC"

        val cursor = db.query(
            TBL_USER,   // The table to query
            list,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val itemIds = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(COLUMN_USER_ID))
                itemIds.add(itemId)
            }
        }

    }

    /** Hace un check del usuario para saber si existe o no según el parámetro de email
     * Hace un return de true/false
     *
     */

    fun checkUser(email: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase
        // seleccoón según el email
        val selection = "$COLUMN_USER_EMAIL = ?"
        // selection argument
        val selectionArgs = arrayOf(email)
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(
            TBL_USER, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }
    /**
     * Si existe usuario según ambos parámetros (Email y password)
     * Hace return de true/false
     *
     *
     *
     */

    fun checkUser(email: String, password: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
        // selection arguments
        val selectionArgs = arrayOf(email, password)
        // query user table with conditions

        val cursor = db.query(
            TBL_USER, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false
    }




}


