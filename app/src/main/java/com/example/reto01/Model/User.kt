package com.example.reto01.Model

data class User(
    var id: Int? = null,
    var name: String? = null,
    var surname: String? = null,
    var email: String? = null,
    var password: String? = null,
    var address: String? = null,
    var city: String? = null,
    var cp: String? = null,
    var description: String? = null,
    var admin: Int? = null
)

