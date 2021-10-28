package com.example.reto01.Model

import java.util.*

data class Order(
    var id_order: Int? = null,
    var id_user: Int? = null,
    var date: String? = null,
    var total: Double? = null,
    var address: String? = null
)
