package com.adrianoca.myapplication.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabaseHelper (context: Context): SQLiteOpenHelper(context,"finanzas.db", null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("""
                CREATE TABLE usuario(
                id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,
                nombres TEXT,
                ape_pat TEXT,
                ape_mat TEXT,
                ocupacion TEXT,
                ingresos REAL,
                fecha_pago TEXT,
                sexo TEXT,
                correo TEXT,
                clave TEXT
                )
                """.trimIndent())

        db.execSQL("""
                CREATE TABLE operaciones(
                id_operacion INTEGER PRIMARY KEY AUTOINCREMENT,
                tipo_operacion TEXT,
                monto REAL,
                fecha TEXT,
                id_usuario INTEGER,
                FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
                )
                """.trimIndent())
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db!!.execSQL("DROP TABLE IF EXISTS usuario")
        db.execSQL("DROP TABLE IF EXISTS operaciones")
        onCreate(db)
    }

}