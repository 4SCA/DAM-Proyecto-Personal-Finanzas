package com.adrianoca.myapplication.entity

data class Usuario(

    val id: Int,
    val nombres: String,
    val apellidoPat: String,
    val apellidoMat: String,
    val ocupacion: String,
    val ingresos: Double,
    val fechaPago: String,
    val sexo: String,
    val correo: String,
    val clave: String,
)
