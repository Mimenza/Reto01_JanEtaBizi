package com.example.reto01

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.reto01.Model.*

class DatabaseHelper(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "janEtaBizi.db"

        // Tables names
        private const val TBL_USERS = "Users"
        private const val TBL_ORDERS = "Orders"
        private const val TBL_PRODUCTS = "Products"
        private const val TBL_ORDERS_PRODUCTS = "Orders_Products"
        private const val TBL_LIKES = "Likes"

        // User Table Columns names
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_SURNAME = "user_surname"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"
        private val COLUMN_USER_ADDRESS = "user_address"
        private val COLUMN_USER_CITY = "user_city"
        private val COLUMN_USER_CP = "user_cp"
        private val COLUMN_USER_DESCRIPTION = "user_description"
        private val COLUMN_USER_ADMIN = "user_admin"
        private val COLUMN_USER_TLF = "user_tlf"
        private val COLUMN_USER_CCV = "user_ccv"
        private val COLUMN_USER_CADUCIDAD = "user_caducidad"
        private val COLUMN_USER_NUM_TARJETA = "user_num_tarjeta"


        // Products Table Columns names
        private val COLUMN_PRODUCT_ID = "product_id"
        private val COLUMN_PRODUCT_NAME = "product_name"
        private val COLUMN_PRODUCT_PRICE = "product_price"
        private val COLUMN_PRODUCT_CATEGORY = "product_category"
        private val COLUMN_PRODUCT_LIKES = "product_likes"
        private val COLUMN_PRODUCT_IMG = "product_img"
        private val COLUMN_PRODUCT_STOCK = "product_stock"
        private val COLUMN_PRODUCT_DESCRIPTION = "product_description"


        // Orders Table Columns names
        private val COLUMN_ORDER_ID = "order_id"
        private val COLUMN_ORDER_USER_ID = "order_user_id"
        private val COLUMN_ORDER_DATE = "order_date"
        private val COLUMN_ORDER_TOTAL = "order_total"
        private val COLUMN_ORDER_ADDRESS = "order_address"

        // Orders_Products Table Columns names
        private val COLUMN_ORDER_PRODUCTS_ORDER_ID = "product_order_id"
        private val COLUMN_ORDER_PRODUCTS_PRODUCT_ID = "product_product_id"
        private val COLUMN_ORDER_PRODUCTS_QUANTITY = "product_product_quantity"


        // Likes Table Columns names
        private val COLUMN_LIKES_USER_ID = "likes_user_id"
        private val COLUMN_LIKES_PRODUCT_ID = "likes_product_id"
    }

    // Create user table sql query
    private val CREATE_USER_TABLE =
        "CREATE TABLE ${TBL_USERS} (" +
                "${COLUMN_USER_ID} INTEGER PRIMARY KEY," +
                "${COLUMN_USER_NAME} TEXT," +
                "${COLUMN_USER_SURNAME} TEXT," +
                "${COLUMN_USER_EMAIL} TEXT," +
                "${COLUMN_USER_PASSWORD} TEXT," +
                "${COLUMN_USER_ADDRESS} TEXT," +
                "${COLUMN_USER_CITY} TEXT," +
                "${COLUMN_USER_CP} TEXT," +
                "${COLUMN_USER_DESCRIPTION} TEXT," +
                "${COLUMN_USER_CADUCIDAD} TEXT," +
                "${COLUMN_USER_CCV} INTEGER," +
                "${COLUMN_USER_TLF} TEXT," +
                "${COLUMN_USER_NUM_TARJETA} TEXT," +
                "${COLUMN_USER_ADMIN} INTEGER)"


    // Create products table sql query
    private val CREATE_PRODUCTS_TABLE =
        "CREATE TABLE ${TBL_PRODUCTS} (" +
                "${COLUMN_PRODUCT_ID} INTEGER PRIMARY KEY," +
                "${COLUMN_PRODUCT_NAME} TEXT," +
                "${COLUMN_PRODUCT_PRICE} DOUBLE," +
                "${COLUMN_PRODUCT_CATEGORY} TEXT," +
                "${COLUMN_PRODUCT_LIKES} INTEGER," +
                "${COLUMN_PRODUCT_IMG} INTEGER," +
                "${COLUMN_PRODUCT_STOCK} INTEGER," +
                "${COLUMN_PRODUCT_DESCRIPTION} TEXT)"

    // Create orders table sql query
    private val CREATE_ORDERS_TABLE =
        "CREATE TABLE ${TBL_ORDERS} (" +
                "${COLUMN_ORDER_ID} INTEGER PRIMARY KEY," +
                "${COLUMN_ORDER_USER_ID} INTEGER," +
                "${COLUMN_ORDER_DATE} DATE," +
                "${COLUMN_ORDER_TOTAL} DOUBLE," +
                "${COLUMN_ORDER_ADDRESS} TEXT," +
                "FOREIGN KEY (${COLUMN_ORDER_USER_ID}) REFERENCES ${TBL_USERS}(${COLUMN_USER_ID}))"

    // Create orders_products table sql query
    private val CREATE_ORDERS_PRODUCTS_TABLE =
        "CREATE TABLE ${TBL_ORDERS_PRODUCTS} (" +
                "${COLUMN_ORDER_PRODUCTS_ORDER_ID} INTEGER," +
                "${COLUMN_ORDER_PRODUCTS_PRODUCT_ID} INTEGER," +
                "${COLUMN_ORDER_PRODUCTS_QUANTITY} INTEGER," +
                "PRIMARY KEY (${COLUMN_ORDER_PRODUCTS_ORDER_ID}, ${COLUMN_ORDER_PRODUCTS_PRODUCT_ID})" +
                "FOREIGN KEY (${COLUMN_ORDER_PRODUCTS_ORDER_ID}) REFERENCES ${TBL_ORDERS}(${COLUMN_ORDER_ID})," +
                "FOREIGN KEY (${COLUMN_ORDER_PRODUCTS_PRODUCT_ID}) REFERENCES ${TBL_PRODUCTS}(${COLUMN_PRODUCT_ID}))"

    // Create orders_products table sql query
    private val CREATE_LIKES_TABLE =
        "CREATE TABLE ${TBL_LIKES} (" +
                "${COLUMN_LIKES_USER_ID} INTEGER," +
                "${COLUMN_LIKES_PRODUCT_ID} INTEGER," +
                "PRIMARY KEY (${COLUMN_LIKES_USER_ID}, ${COLUMN_LIKES_PRODUCT_ID})" +
                "FOREIGN KEY (${COLUMN_LIKES_USER_ID}) REFERENCES ${TBL_USERS}(${COLUMN_USER_ID})," +
                "FOREIGN KEY (${COLUMN_LIKES_PRODUCT_ID}) REFERENCES ${TBL_PRODUCTS}(${COLUMN_PRODUCT_ID}))"


    //Load productos
    //private  val LOAD_PRODUCTS = "INSERT INTO ${TBL_PRODUCTS}  VALUES ( -1 ,'Pepinillos', 10.00 , 'vegan', 12, 1)"

    private  val LOAD_PRODUCTS =
        "INSERT INTO ${TBL_PRODUCTS}  VALUES " +
                //ALERGIA HUEVO
                "( 0 ,"+R.string.mayonesa+", 3.54 ,'huevo', 12,"+R.drawable.alergeno_huevo1+", 10, "+R.string.mayonesadescripcion+"), " +
                "( 1 ,"+R.string.harinatrigo+", 2.39 , 'huevo', 12, "+R.drawable.alergeno_huevo2+", 10, "+R.string.harinatrigodescripcion+"), " +
                "( 2 ,"+R.string.pepitaschoco+", 3.49 , 'huevo', 12, "+R.drawable.alergeno_huevo3+", 10, "+R.string.pepitaschocodescripcion+"), " +
                //ALERGIA LECHE
                "( 3 ,"+R.string.aceitecoco+", 4.63 , 'lacteo', 12,"+R.drawable.alergeno_leche1+", 10, "+R.string.aceitecocodescripcion+"), " +
                "( 4 ,"+R.string.biscotesespelta+", 3.70 , 'lacteo', 12,"+R.drawable.alergeno_leche2+", 10, "+R.string.bicotesdescripcion+") ," +
                "( 5 ,"+R.string.cafeleche+", 3.60 ,  'lacteo', 12,"+R.drawable.alergeno_leche3+", 10, "+R.string.lechecafedescripcion+"), " +
                //CELIACO
                "( 6 ,"+R.string.macarrones+", 1.85 , 'celiaco', 12, "+R.drawable.celiaco1+", 10, "+R.string.macarronesdescripcion+"), " +
                //KM0
                "( 7 ,"+R.string.brocoli+", 2.40 , 'KM0', 12,"+R.drawable.km01+" , 10, "+R.string.brocolidescripcion+"), " +
                "( 8 ,"+R.string.puerro+", 2.99 , 'KM0', 12,"+R.drawable.km02+" , 10, "+R.string.puerrodescripcion+") ," +
                "( 9 ,"+R.string.pimientorojo+", 3.50 , 'KM0', 12,"+R.drawable.km03+", 10, "+R.string.pimientorojodescripcion+"), " +
                "( 10 ,"+R.string.tomate+", 3.70 , 'KM0', 12,"+R.drawable.km04+" , 10, "+R.string.tomatedescripcion+"), " +
                //DEPORTISTAS
                "( 13 ,"+R.string.proteinaplatano+", 23.30 , 'deporte', 12,"+R.drawable.deportistas1+", 10, "+R.string.proteinaplatanoadescripcion+"), " +
                "( 14 ,"+R.string.citratomagnesio+", 23.49 , 'deporte', 12,"+R.drawable.deportistas2+", 10, "+R.string.citratomagnesiodescripcion+") ," +
                "( 15 ,"+R.string.impactwheyprotein+", 25.99 , 'deporte', 12,"+R.drawable.deportistas3+", 10, "+R.string.impactwheydescripcion+"), " +
                "( 16 ,"+R.string.omegavegano+", 29.99 , 'deporte', 12, "+R.drawable.deportistas4+", 10, "+R.string.mayonesadescripcion+"), " +
                "( 17 ,"+R.string.musclepack+", 49.99 , 'deporte', 12, "+R.drawable.deportistas5+", 10, "+R.string.musclepackdescripcion+"), " +
                //VEGANOS
                "( 18 ,"+R.string.macarronesveganos+", 2.10 ,'vegan', 12, "+R.drawable.vegano1+", 10, "+R.string.macarronesdescripcion+"), " +
                "( 19 ,"+R.string.hamburguesavegana+", 3.99 , 'vegan', 12,"+R.drawable.vegano2+", 10, "+R.string.hamburguesadescripcion+") ," +
                "( 20 ,"+R.string.albondigasvegano+", 3.99 , 'vegan', 12,"+R.drawable.vegano3+", 10, "+R.string.albondigasdescripcion+"), " +
                "( 21 ,"+R.string.alcachofas+", 3.70 , 'vegan', 12, "+R.drawable.vegano4+", 10, "+R.string.alcachofasdescripcion+"), " +
                "( 22 ,"+R.string.judiasypatatas+", 2.75 , 'vegan', 12, "+R.drawable.vegano5+", 10, "+R.string.judiasypatatasdescripcion+") "




    // Drop tables sql query
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS ${TBL_USERS}"
    private val DROP_PRODUCTS_TABLE = "DROP TABLE IF EXISTS ${TBL_PRODUCTS}"
    private val DROP_ORDERS_TABLE = "DROP TABLE IF EXISTS ${TBL_ORDERS}"
    private val DROP_ORDERS_PRODUCTS_TABLE = "DROP TABLE IF EXISTS ${TBL_ORDERS_PRODUCTS}"
    private val DROP_LIKES_TABLE = "DROP TABLE IF EXISTS ${TBL_LIKES}"

    // Create tables sql query
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
        db.execSQL(CREATE_PRODUCTS_TABLE)
        db.execSQL(CREATE_ORDERS_TABLE)
        db.execSQL(CREATE_ORDERS_PRODUCTS_TABLE)
        db.execSQL(CREATE_LIKES_TABLE)
        db.execSQL(LOAD_PRODUCTS)
    }

    // Drop & Create tables sql query
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        db.execSQL(DROP_PRODUCTS_TABLE)
        db.execSQL(DROP_ORDERS_TABLE)
        db.execSQL(DROP_ORDERS_PRODUCTS_TABLE)
        db.execSQL(DROP_LIKES_TABLE)
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
        values.put(COLUMN_USER_ADMIN, user.admin)
        values.put(COLUMN_USER_CP, user.cp)
        values.put(COLUMN_USER_CITY, user.city)
        values.put(COLUMN_USER_ADDRESS, user.address)
        values.put(COLUMN_USER_SURNAME, user.surname)
        values.put(COLUMN_USER_DESCRIPTION, user.description)
        values.put(COLUMN_USER_CCV, user.ccv)
        values.put(COLUMN_USER_NUM_TARJETA, user.num_tarjeta)
        values.put(COLUMN_USER_TLF, user.tlf)
        values.put(COLUMN_USER_CADUCIDAD, user.caducidad)

        // Inserting Row
        db.insert(TBL_USERS, null, values)
        db.close()
    }

    //Actualizar usuario
    fun updateUser(user: User) {
        // Gets the data repository in write mode
        val db = this.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put("user_id", user.id)
            put("user_name", user.name)
            put("user_email", user.email)
            put("user_password", user.password)
            put("user_admin", user.admin)
            put("user_surname", user.surname)
            put("user_address", user.address)
            put("user_city", user.city)
            put("user_description", user.description)
            put("user_cp", user.cp)
            put("user_tlf", user.tlf)
            put("user_ccv", user.ccv)
            put("user_caducidad", user.caducidad)
            put("user_num_tarjeta", user.num_tarjeta)
        }
        // update según el id de usuario
        db.update(
            TBL_USERS, values, "${COLUMN_USER_ID} = ?",
            arrayOf(user.id.toString())
        )
        db.close()
    }

    //Actualizar usuario
    fun updateProduct(product: Producto) {
        // Gets the data repository in write mode
        val db = this.writableDatabase
        println("DB"+ product)
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put("product_id", product.id_product)
            put("product_name", product.name_product)
            put("product_category", product.category)
            put("product_price", product.price)
            put("product_img", product.img)
            put("product_likes", product.likes)
            put("product_stock", product.stock)
            put("product_description", product.description)

        }
        // update según el id de usuario
        db.update(
            TBL_PRODUCTS, values, "${COLUMN_PRODUCT_ID} = ?",
            arrayOf(product.id_product.toString())
        )
        db.close()
    }
    //Eliminar usuario por correo
    fun deleteUser(correo: String?) {
        val db = this.writableDatabase
        // Eliminar usuario según id
        db.delete(

            TBL_USERS, "${COLUMN_PRODUCT_ID}= ?",
            arrayOf(correo)
        )


        db.close()
    }

    //Eliminar usuario por correo
    fun deleteUserById(id: Int?) {
        val db = this.writableDatabase
        // Eliminar usuario según id
        db.delete(

            TBL_USERS, "${COLUMN_USER_ID}= ?",
            arrayOf(id.toString())
        )


        db.close()
    }

    //Eliminar usuario
    fun deleteProduct(id_product: Int?) {
        val db = this.writableDatabase
        // Eliminar usuario según id
        db.delete(

            TBL_PRODUCTS, "${COLUMN_PRODUCT_ID}= ?",
            arrayOf(id_product.toString())
        )


        db.close()
    }

    //Insertar order
    fun addOrder(order: Order): ContentValues {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_ORDER_ADDRESS, order.address)
        values.put(COLUMN_ORDER_DATE, order.date)
        values.put(COLUMN_ORDER_USER_ID, order.id_user)
        values.put(COLUMN_ORDER_TOTAL, order.total)

        // Inserting Row
        db.insert(TBL_ORDERS, null, values)
        db.close()

        return values
    }

    fun addOrder_product(pedidoProducto: Pedido_producto) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_ORDER_PRODUCTS_ORDER_ID, pedidoProducto.id_order)
        values.put(COLUMN_ORDER_PRODUCTS_PRODUCT_ID, pedidoProducto.id_product)
        values.put(COLUMN_ORDER_PRODUCTS_QUANTITY, pedidoProducto.quantity)

        // Inserting Row
        db.insert(TBL_ORDERS_PRODUCTS, null, values)
        db.close()

    }

    @SuppressLint("Range")
    fun lastOrder(): Order? {

        val db: SQLiteDatabase = this.getReadableDatabase()
        val res = db.rawQuery(
            "select * from Orders where Orders.order_id =(SELECT MAX(order_id)  FROM Orders)",
            null
        )
        res.moveToFirst()

        while (res.isAfterLast == false) {
            val response = Order()
            response.id_user = res.getInt(res.getColumnIndex(COLUMN_ORDER_USER_ID))
            response.address = res.getString(res.getColumnIndex(COLUMN_ORDER_ADDRESS))
            response.id_order = res.getInt(res.getColumnIndex(COLUMN_ORDER_ID))
            response.date = res.getString(res.getColumnIndex(COLUMN_ORDER_DATE))
            response.total = res.getDouble(res.getColumnIndex(COLUMN_ORDER_TOTAL))

            // rest of columns
            return response
        }
        return null


    }
    //Lista que hace return de todos los usuarios
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */


    @SuppressLint("Range")
    fun getAllUser(): List<User> {
        // array of columns to fetch
        val columns = arrayOf(
            COLUMN_USER_ID,
            COLUMN_USER_NAME,
            COLUMN_USER_SURNAME,
            COLUMN_USER_EMAIL,
            COLUMN_USER_PASSWORD,
            COLUMN_USER_ADDRESS,
            COLUMN_USER_CITY,
            COLUMN_USER_CP,
            COLUMN_USER_DESCRIPTION,
            COLUMN_USER_ADMIN,
            COLUMN_USER_CCV,
            COLUMN_USER_CADUCIDAD,
            COLUMN_USER_NUM_TARJETA,
            COLUMN_USER_TLF
        )
        // sorting orders
        val sortOrder = "$COLUMN_USER_NAME ASC"
        val userList: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        // query the user table
        val cursor = db.query(
            TBL_USERS, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
                    password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)),
                    surname = cursor.getString(cursor.getColumnIndex(COLUMN_USER_SURNAME)),
                    email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)),
                    address = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ADDRESS)),
                    city = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CITY)),
                    cp = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CP)),
                    description = cursor.getString(cursor.getColumnIndex(COLUMN_USER_DESCRIPTION)),
                    tlf = cursor.getString(cursor.getColumnIndex(COLUMN_USER_TLF)),
                    num_tarjeta = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NUM_TARJETA)),
                    caducidad = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CADUCIDAD)),
                    ccv = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_CCV)),
                    admin = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ADMIN)),
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }

    @SuppressLint("Range")
    fun getAllOrders(): List<Order> {
        // array of columns to fetch
        val columns = arrayOf(
            COLUMN_ORDER_ADDRESS,
            COLUMN_ORDER_DATE,
            COLUMN_ORDER_ID,
            COLUMN_ORDER_TOTAL,
            COLUMN_ORDER_USER_ID
        )
        // sorting orders
        val sortOrder = "$COLUMN_ORDER_ID DESC"
        val orderList: MutableList<Order> = ArrayList()
        val db = this.readableDatabase
        // query the user table
        val cursor = db.query(
            TBL_ORDERS, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val order = Order(
                    address = cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_ADDRESS)),
                    id_order = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_ID)),
                    id_user = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_USER_ID)),
                    date = cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DATE)),
                    total = cursor.getDouble(cursor.getColumnIndex(COLUMN_ORDER_TOTAL)),

                    )
                orderList.add(order)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return orderList
    }

    /** Hace un check del usuario para saber si existe o no según el parámetro de email
     * Hace un return de true/false
     *
     */

    @SuppressLint("Range")
    fun getAllProducts(): List<Producto> {
        // array of columns to fetch
        val columns = arrayOf(
            COLUMN_PRODUCT_ID, COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_CATEGORY, COLUMN_PRODUCT_LIKES,
            COLUMN_PRODUCT_PRICE, COLUMN_PRODUCT_IMG, COLUMN_PRODUCT_STOCK, COLUMN_PRODUCT_DESCRIPTION)
        //val columns = arrayOf(COLUMN_PRODUCT_ID, COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_CATEGORY, COLUMN_PRODUCT_LIKES
        //   COLUMN_PRODUCT_PRICE, COLUMN_PRODUCT_IMG, COLUMN_PRODUCT_STOCK )
        // sorting orders
        val sortOrder = "$COLUMN_PRODUCT_LIKES DESC"
        val productList: MutableList<Producto> = ArrayList()
        val db = this.readableDatabase
        // query the user table
        val cursor = db.query(
            TBL_PRODUCTS, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val producto = Producto(
                    id_product = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)),
                    name_product = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)),
                    price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)),
                    category = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY)),
                    stock = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_STOCK)),
                    img = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_IMG)),
                    likes = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_LIKES)),
                    description = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)),

                    )
                productList.add(producto)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return productList
    }

    @SuppressLint("Range")
    fun getProductsByCategory(category: String): List<Producto> {
        val db: SQLiteDatabase = this.getReadableDatabase()
        val columns = arrayOf(
            COLUMN_PRODUCT_ID, COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_CATEGORY, COLUMN_PRODUCT_LIKES,
            COLUMN_PRODUCT_PRICE, COLUMN_PRODUCT_IMG, COLUMN_PRODUCT_STOCK
        )
        val productList: MutableList<Producto> = ArrayList()
        val res =
            db.rawQuery("select * from Products where product_category='" + category + "'", null)
        res.moveToFirst()
        val cursor = db.query(
            TBL_PRODUCTS, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            null
        )         //The sort order

        if (cursor.moveToFirst()) {
            do {
                val producto = Producto(
                    id_product = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)),
                    name_product = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)),
                    price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)),
                    category = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY)),
                    stock = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_STOCK)),
                    img = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_IMG)),
                    likes = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_LIKES))
                )
                productList.add(producto)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return productList

    }

    //Get product object by id_product
    @SuppressLint("Range")
    fun getProduct(id: Int): Producto? {
        val db: SQLiteDatabase = this.getReadableDatabase()
        val res = db.rawQuery("select * from Products where product_id='" + id + "'", null)
        res.moveToFirst()

        while (res.isAfterLast == false) {
            val response = Producto()


            response.id_product = res.getInt(res.getColumnIndex(COLUMN_PRODUCT_ID))
            response.name_product = res.getString(res.getColumnIndex(COLUMN_PRODUCT_NAME))
            response.price = res.getDouble(res.getColumnIndex(COLUMN_PRODUCT_PRICE))
            response.category = res.getString(res.getColumnIndex(COLUMN_PRODUCT_CATEGORY))
            response.likes = res.getInt(res.getColumnIndex(COLUMN_PRODUCT_LIKES))
            response.img = res.getInt(res.getColumnIndex(COLUMN_PRODUCT_IMG))
            response.stock = res.getInt(res.getColumnIndex(COLUMN_PRODUCT_STOCK))
            response.description = res.getString(res.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION))
            // rest of columns
            return response
        }
        return null


    }

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
            null
        )  //The sort order
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
            null
        ) //The sort order
        val cursorCount = cursor.count
        cursor.close()

        if (cursorCount > 0)
            return true
        return false
    }

    @SuppressLint("Range")
    fun getUser(email: String): User? {
        val db: SQLiteDatabase = this.getReadableDatabase()
        val res = db.rawQuery("select * from Users where user_email='" + email + "'", null)
        res.moveToFirst()

        while (res.isAfterLast == false) {
            val response = User()
            response.id = res.getInt(res.getColumnIndex(COLUMN_USER_ID))
            response.password = res.getString(res.getColumnIndex(COLUMN_USER_PASSWORD))
            response.name = res.getString(res.getColumnIndex(COLUMN_USER_NAME))
            response.surname = res.getString(res.getColumnIndex(COLUMN_USER_SURNAME))
            response.email = res.getString(res.getColumnIndex(COLUMN_USER_EMAIL))
            response.password = res.getString(res.getColumnIndex(COLUMN_USER_PASSWORD))
            response.tlf = res.getString(res.getColumnIndex(COLUMN_USER_TLF))
            response.address = res.getString(res.getColumnIndex(COLUMN_USER_ADDRESS))
            response.ccv = res.getInt(res.getColumnIndex(COLUMN_USER_CCV))
            response.caducidad = res.getString(res.getColumnIndex(COLUMN_USER_CADUCIDAD))
            response.num_tarjeta = res.getString(res.getColumnIndex(COLUMN_USER_NUM_TARJETA))
            response.admin = res.getInt(res.getColumnIndex(COLUMN_USER_ADMIN))
            response.city = res.getString(res.getColumnIndex(COLUMN_USER_CITY))
            response.cp = res.getString(res.getColumnIndex(COLUMN_USER_CP))
            response.description = res.getString(res.getColumnIndex(COLUMN_USER_DESCRIPTION))


            // rest of columns
            return response
        }
        return null


    }

    @SuppressLint("Range")
    fun getUserId(id: Int): User? {
        val db: SQLiteDatabase = this.getReadableDatabase()
        val res = db.rawQuery("select * from Users where user_id='" + id + "'", null)
        res.moveToFirst()

        while (res.isAfterLast == false) {
            val response = User()
            response.id = res.getInt(res.getColumnIndex(COLUMN_USER_ID))
            response.password = res.getString(res.getColumnIndex(COLUMN_USER_PASSWORD))
            response.name = res.getString(res.getColumnIndex(COLUMN_USER_NAME))
            response.surname = res.getString(res.getColumnIndex(COLUMN_USER_SURNAME))
            response.email = res.getString(res.getColumnIndex(COLUMN_USER_EMAIL))
            response.password = res.getString(res.getColumnIndex(COLUMN_USER_PASSWORD))
            response.tlf = res.getString(res.getColumnIndex(COLUMN_USER_TLF))
            response.address = res.getString(res.getColumnIndex(COLUMN_USER_ADDRESS))
            response.ccv = res.getInt(res.getColumnIndex(COLUMN_USER_CCV))
            response.caducidad = res.getString(res.getColumnIndex(COLUMN_USER_CADUCIDAD))
            response.num_tarjeta = res.getString(res.getColumnIndex(COLUMN_USER_NUM_TARJETA))
            response.admin = res.getInt(res.getColumnIndex(COLUMN_USER_ADMIN))
            response.city = res.getString(res.getColumnIndex(COLUMN_USER_CITY))
            response.cp = res.getString(res.getColumnIndex(COLUMN_USER_CP))
            response.description = res.getString(res.getColumnIndex(COLUMN_USER_DESCRIPTION))


            // rest of columns
            return response
        }
        return null


    }

    @SuppressLint("Range")
    fun getPedidoProducto(id_Order: Int): ArrayList<Pedido_producto> {
        val arrayPedidos = ArrayList<Pedido_producto>()

        val db: SQLiteDatabase = this.getReadableDatabase()
        val res = db.rawQuery("select * from Orders_Products where product_order_id='" + id_Order + "'", null)
        res.moveToFirst()

        while (res.isAfterLast == false) {

            val response = Pedido_producto()
            response.quantity = res.getInt(res.getColumnIndex(COLUMN_ORDER_PRODUCTS_QUANTITY))
            response.id_product = res.getInt(res.getColumnIndex(COLUMN_ORDER_PRODUCTS_PRODUCT_ID))
            response.id_order = res.getInt(res.getColumnIndex(COLUMN_ORDER_PRODUCTS_ORDER_ID))

            // rest of columns
            println(response)
            arrayPedidos.add(response)
        }


        return arrayPedidos


    }

    @SuppressLint("Range")
    fun getPedidoProducto1(id_Order: Int): MutableList<Pedido_producto> {
        val db: SQLiteDatabase = this.getReadableDatabase()
        val columns = arrayOf(
            COLUMN_ORDER_PRODUCTS_ORDER_ID, COLUMN_ORDER_PRODUCTS_PRODUCT_ID, COLUMN_ORDER_PRODUCTS_QUANTITY
        )
        val listPedidos: MutableList<Pedido_producto> = ArrayList()
        val res = db.rawQuery("select * from Orders_Products where product_order_id='" + id_Order + "'", null)
        res.moveToFirst()
        val cursor = db.query(
            TBL_ORDERS_PRODUCTS, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            null
        )         //The sort order

        if (cursor.moveToFirst()) {
            do {
                val  pedidoProducto = Pedido_producto(
                    id_product = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_PRODUCTS_PRODUCT_ID)),
                    id_order = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_PRODUCTS_ORDER_ID)),
                    quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_PRODUCTS_QUANTITY))

                )
                listPedidos.add(pedidoProducto)

            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return listPedidos

    }



    fun generatelike(idProduct: Int?, id: Int?) {
        //Funcion que genera like en la DB
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_LIKES_PRODUCT_ID, idProduct)
        values.put(COLUMN_LIKES_USER_ID, id)

        // Inserting Row
        db.insert(TBL_LIKES, null, values)
        db.close()

    }

    fun deletelike(idProduct: Int?) {
        //Funcion que borra el like de la DB

        val db = this.writableDatabase

        // Inserting Row
        db.delete(
            TBL_LIKES, "${COLUMN_LIKES_PRODUCT_ID} = ? ",
            arrayOf(idProduct.toString())
        )
        db.close()

    }

    fun checkLike(idProduct: Int?, id: Int?): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_LIKES_PRODUCT_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_LIKES_PRODUCT_ID = ? AND $COLUMN_LIKES_USER_ID = ?"

        // selection arguments
        val selectionArgs = arrayOf(idProduct.toString(), id.toString())

        // query user table with conditions
        val cursor = db.query(
            TBL_LIKES, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null
        ) //The sort order
        val cursorCount = cursor.count
        cursor.close()

        if (cursorCount > 0)
            return true
        return false
    }

    @SuppressLint("Range")
    fun getUserLikes(id: Int?): MutableList<Likes> {
        val db: SQLiteDatabase = this.getReadableDatabase()
        val columns = arrayOf(
            COLUMN_LIKES_PRODUCT_ID, COLUMN_LIKES_USER_ID
        )
        val likesList: MutableList<Likes> = ArrayList()
        val res =
            db.rawQuery("select * from Likes where likes_user_id='" + id + "'", null)
        res.moveToFirst()
        val cursor = db.query(
            TBL_LIKES, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            null
        )         //The sort order

        if (cursor.moveToFirst()) {
            do {
                val like = Likes(
                    id_user = cursor.getInt(cursor.getColumnIndex(COLUMN_LIKES_USER_ID)),
                    id_producto = cursor.getInt(cursor.getColumnIndex(COLUMN_LIKES_PRODUCT_ID)),
                )
                likesList.add(like)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return likesList

    }

}
