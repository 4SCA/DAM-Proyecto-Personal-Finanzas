package com.adrianoca.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.adrianoca.myapplication.R
import com.adrianoca.myapplication.RegistrarOperacion

class RegistrarFinanzaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registrar_finanza, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnGasto = view.findViewById<Button>(R.id.btnRegistrarGasto)
        val btnIngreso = view.findViewById<Button>(R.id.btnRegistrarIngreso)

        btnGasto.setOnClickListener {
            val intent = Intent(requireContext(), RegistrarOperacion::class.java)
            intent.putExtra("tipoOperacion", "Gasto")
            startActivity(intent)
        }

        btnIngreso.setOnClickListener {
            val intent = Intent(requireContext(), RegistrarOperacion::class.java)
            intent.putExtra("tipoOperacion", "Ingreso")
            startActivity(intent)
        }
    }
}