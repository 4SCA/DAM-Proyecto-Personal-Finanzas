package com.adrianoca.myapplication.entity

data class Operacion(
    val idOperacion : Int,
    val tipoOperacion : String,
    val monto : Double,
    val fecha : String,
    val idUsuario: Int,
)
