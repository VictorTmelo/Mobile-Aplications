package com.example.bluepocket.entity

data class Despesa(
    val id: String = "",
    var nome: String = "",
    var tipo: String = "",
    var data: Long = 0,
    var valor: Double = 0.0,
    var typeId: String = "",
    var userID: String = ""
)