package com.example.reto01.Model

data class Producto(
    val id_product: Int? = null,
    val name_product: String? = null,
    val price: Double? = null,
    val category: String? = null,
    val stock: Int = 1,
    val likes: Int? = null,
    val img: Int = 1
)