package com.adrianoca.myapplication.data

import android.content.ContentValues
import android.content.Context
import com.adrianoca.myapplication.entity.Usuario

class UsuarioDAO(context: Context) {
    private val dbHelper = AppDatabaseHelper(context)

    fun insertar(usuario: Usuario): Long{
        val db = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("nombres", usuario.nombres)
            put("ape_pat", usuario.apellidoPat)
            put("ape_mat", usuario.apellidoMat)
            put("ocupacion", usuario.ocupacion)
            put("ingresos", usuario.ingresos)
            put("fecha_pago", usuario.fechaPago)
            put("sexo", usuario.sexo)
            put("correo", usuario.correo)
            put("clave", usuario.clave)
        }
        return db.insert("usuarios", null, valores)
    }
    fun buscarUsuarioPorCorreoyClave(correo: String, clave: String): Usuario? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE correo = ? AND clave = ?",
            arrayOf(correo, clave)
        )
        var usuario: Usuario? = null
        if (cursor.moveToFirst()) {
            usuario = Usuario(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombres = cursor.getString(cursor.getColumnIndexOrThrow("nombres")),
                apellidoPat = cursor.getString(cursor.getColumnIndexOrThrow("ape_pat")),
                apellidoMat = cursor.getString(cursor.getColumnIndexOrThrow("ape_mat")),
                ocupacion = cursor.getString(cursor.getColumnIndexOrThrow("ocupacion")),
                ingresos = cursor.getDouble(cursor.getColumnIndexOrThrow("ingresos")),
                fechaPago = cursor.getString(cursor.getColumnIndexOrThrow("fecha_pago")),
                sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo")),
                correo = cursor.getString(cursor.getColumnIndexOrThrow("correo")),
                clave = cursor.getString(cursor.getColumnIndexOrThrow("clave"))
            )
        }
        cursor.close()
        return usuario
    }
}