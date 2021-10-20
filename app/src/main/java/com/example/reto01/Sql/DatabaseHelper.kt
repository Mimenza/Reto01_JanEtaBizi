package com.example.reto01

import android.content.ContentValues
import android.content.Context
import android.content.LocusId
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.reto01.Model.User

class DatabaseHelper(context:Context, name: String, factory: SQLiteDatabase.CursorFactory?, version:Int) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "janEtaBizi.db"

        // Tables names
        private const val TBL_USERS = "Users"
        private const val TBL_ORDERS = "Orders"
        private const val TBL_PRODUCTS = "Products"
        private const val TBL_ORDERS_PRODUCTS = "Orders_Products"

        // User Table Columns names
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"
        private val COLUMN_USER_ADMIN = "user_admin"

        // Products Table Columns names
        private val COLUMN_PRODUCT_ID = "product_id"
        private val COLUMN_PRODUCT_NAME = "product_name"
        private val COLUMN_PRODUCT_PRICE = "product_price"
        private val COLUMN_PRODUCT_CATEGORY = "product_category"
        private val COLUMN_PRODUCT_LIKES = "product_likes"
        private val COLUMN_PRODCUCT_IMG = "product_img"
        // Orders Table Columns names
        private val COLUMN_ORDER_ID = "order_id"
        private val COLUMN_ORDER_USER_ID = "order_user_id"
        private val COLUMN_ORDER_DATE = "order_date"
        private val COLUMN_ORDER_TOTAL = "order_total"
        private val COLUMN_ORDER_ADDRESS = "order_admin"

        // Orders_Products Table Columns names
        private val COLUMN_ORDER_PRODUCTS_ORDER_ID = "order_product_id"
        private val COLUMN_ORDER_PRODUCTS_PRODUCT_ID = "product_name"
        private val COLUMN_ORDER_PRODUCTS_QUANTITY = "product_price"
    }

    // Create user table sql query
    private  val CREATE_USER_TABLE =
        "CREATE TABLE ${TBL_USERS} (" +
                "${COLUMN_USER_ID} INTEGER PRIMARY KEY," +
                "${COLUMN_USER_NAME} TEXT," +
                "${COLUMN_USER_EMAIL} TEXT," +
                "${COLUMN_USER_PASSWORD} TEXT," +
                "${COLUMN_USER_ADMIN} INTEGER)"

    // Create products table sql query
    private  val CREATE_PRODUCTS_TABLE =
        "CREATE TABLE ${TBL_PRODUCTS} (" +
                "${COLUMN_PRODUCT_ID} INTEGER PRIMARY KEY," +
                "${COLUMN_PRODUCT_NAME} TEXT," +
                "${COLUMN_PRODUCT_PRICE} DOUBLE," +
                "${COLUMN_PRODUCT_CATEGORY} TEXT," +
                "${COLUMN_PRODUCT_LIKES} INTEGER" +
                "${COLUMN_PRODCUCT_IMG} TEXT)"

    // Create orders table sql query
    private  val CREATE_ORDERS_TABLE =
        "CREATE TABLE ${TBL_ORDERS} (" +
                "${COLUMN_ORDER_ID} INTEGER PRIMARY KEY," +
                "${COLUMN_ORDER_USER_ID} INTEGER," +
                "${COLUMN_ORDER_DATE} DATE," +
                "${COLUMN_ORDER_TOTAL} DOUBLE," +
                "${COLUMN_ORDER_ADDRESS} TEXT," +
                "FOREIGN KEY (${COLUMN_ORDER_USER_ID}) REFERENCES ${TBL_USERS}(${COLUMN_USER_ID}))"

    // Create orders_products table sql query
    private  val CREATE_ORDERS_PRODUCTS_TABLE =
        "CREATE TABLE ${TBL_ORDERS_PRODUCTS} (" +
                "${COLUMN_ORDER_PRODUCTS_ORDER_ID} INTEGER," +
                "${COLUMN_ORDER_PRODUCTS_PRODUCT_ID} INTEGER," +
                "${COLUMN_ORDER_PRODUCTS_QUANTITY} INTEGER," +
                "PRIMARY KEY (${COLUMN_ORDER_PRODUCTS_ORDER_ID}, ${COLUMN_ORDER_PRODUCTS_PRODUCT_ID})" +
                "FOREIGN KEY (${COLUMN_ORDER_PRODUCTS_ORDER_ID}) REFERENCES ${TBL_ORDERS}(${COLUMN_ORDER_ID})," +
                "FOREIGN KEY (${COLUMN_ORDER_PRODUCTS_PRODUCT_ID}) REFERENCES ${TBL_PRODUCTS}(${COLUMN_PRODUCT_ID}))"


    // Drop tables sql query
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS ${TBL_USERS}"
    private val DROP_PRODUCTS_TABLE = "DROP TABLE IF EXISTS ${TBL_PRODUCTS}"
    private val DROP_ORDERS_TABLE = "DROP TABLE IF EXISTS ${TBL_ORDERS}"
    private val DROP_ORDERS_PRODUCTS_TABLE = "DROP TABLE IF EXISTS ${TBL_ORDERS_PRODUCTS}"

    // Create tables sql query
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
        db.execSQL(CREATE_PRODUCTS_TABLE)
        db.execSQL(CREATE_ORDERS_TABLE)
        db.execSQL(CREATE_ORDERS_PRODUCTS_TABLE)
    }

    // Drop & Create tables sql query
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        db.execSQL(DROP_PRODUCTS_TABLE)
        db.execSQL(DROP_ORDERS_TABLE)
        db.execSQL(DROP_ORDERS_PRODUCTS_TABLE)
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
        db.insert(TBL_USERS, null, values)
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
            TBL_USERS, values, "${COLUMN_USER_ID} = ?",
            arrayOf(user.id.toString()))
        db.close()
    }

    //Eliminar usuario
    fun deleteUser(user: User)   {
        val db = this.writableDatabase
        // Eliminar usuario según id
        db.delete(

            TBL_USERS, "${COLUMN_USER_ID}= ?",
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
            TBL_USERS,   // The table to query
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
        val cursor = db.query(
            TBL_USERS, //Table to query
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
            TBL_USERS, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()

        if (cursorCount > 0)
            return true
        return false
    }
}
