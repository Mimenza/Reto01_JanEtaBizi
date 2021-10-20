package com.example.reto01.Model

import java.net.Inet4Address
import java.util.*


data class Order(val id_order: Int = -1, val id_user: Int= -1, val date:Date, val total: Int, val address: String)