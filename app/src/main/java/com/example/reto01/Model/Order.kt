package com.example.reto01.Model

import java.util.*

data class Order(
    val id_order: Int? = null,
    val id_user: Int? = null,
    val date: Date? = null,
    val total: Int? = null,
    val address: String? = null
)
