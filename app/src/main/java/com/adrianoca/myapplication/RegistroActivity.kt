package com.adrianoca.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        btnRegresar.setOnClickListener{
            cambioActivity(MainActivity::class.java)
        }

        btnRegistrar.setOnClickListener {
            cambioActivity(MainActivity::class.java)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun cambioActivity(activityDestino : Class<out Activity>) {
        val intent = Intent(this, activityDestino)
        startActivity(intent)
    }
}