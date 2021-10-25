package com.example.reto01.Model

data class Producto(
    var id_product: Int? = null,
    var name_product: String? = null,
    var price: Double? = null,
    var category: String? = null,
    var stock: Int? = null,
    var img: Int = 1,
    var likes: Int? = null
)