package com.adrianoca.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adrianoca.myapplication.entity.Operacion
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegistrarOperacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_operacion)
        val tvFechaOperacion = findViewById<TextView>(R.id.tvFechaOperacion)

        val fechaFormateada = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("es", "PE")).format(Date())
        val tvTipoOperacion = findViewById<TextView>(R.id.tvTipoOperacion)
        val tietMonto = findViewById<TextInputEditText>(R.id.tietMonto)
        val btnAceptar = findViewById<Button>(R.id.btnAceptar)
        val btnCancelar = findViewById<Button>(R.id.btnCancelar)



        val tipoOperacion = intent.getStringExtra("tipoOperacion")?:"Desconocido"

        tvFechaOperacion.text = "Fecha: $fechaFormateada"
        tvTipoOperacion.text = "Tipo de operación: $tipoOperacion"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnCancelar.setOnClickListener {
            cambioActivity(InicioActivity::class.java)
        }
       btnAceptar.setOnClickListener {
           cambioActivity(InicioActivity::class.java)
        }
    }

    fun cambioActivity(activityDestino : Class<out Activity>) {
        val intent = Intent(this, activityDestino)
        startActivity(intent)
    }
}