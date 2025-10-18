package com.adrianoca.myapplication.data

import android.content.ContentValues
import android.content.Context
import com.adrianoca.myapplication.entity.Operacion

class OperacionDAO(context: Context){
    private val dbHelper = AppDatabaseHelper(context)
    fun insertarOperacion(operacion: Operacion):Long{
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("tipo_operacion",operacion.tipoOperacion)
            put("monto",operacion.monto)
            put("fecha",operacion.fecha)
        }
        return db.insert("operaciones",null,values)
    }
    fun listarOperacionesPorUsuario(idUsuario: Int): List<Operacion> {
        val db = dbHelper.readableDatabase
        val lista = mutableListOf<Operacion>()
        val cursor = db.rawQuery(
            "SELECT * FROM operaciones WHERE id_usuario = ?",
            arrayOf(idUsuario.toString())
        )
        if (cursor.moveToFirst()) {
            do {
                val operacion = Operacion(
                    idOperacion = cursor.getInt(cursor.getColumnIndexOrThrow("id_operacion")),
                    tipoOperacion = cursor.getString(cursor.getColumnIndexOrThrow("tipo_operacion")),
                    monto = cursor.getDouble(cursor.getColumnIndexOrThrow("monto")),
                    fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha")),
                    idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario"))
                )
                lista.add(operacion)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

}
