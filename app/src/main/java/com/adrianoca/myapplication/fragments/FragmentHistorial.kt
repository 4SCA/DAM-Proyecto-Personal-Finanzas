package com.adrianoca.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrianoca.myapplication.R
import com.adrianoca.myapplication.adapters.HistorialAdapter
import com.adrianoca.myapplication.entity.Operacion

class FragmentHistorial : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistorialAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_historial, container, false)
        recyclerView = view.findViewById(R.id.rvHistorial)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val listaOperaciones = listOf(
            Operacion(
                idOperacion = 1,
                tipoOperacion = "Depósito",
                monto = 150.0,
                fecha = "18/10/2025 14:30",
                idUsuario = 101
            ),
            Operacion(
                idOperacion = 2,
                tipoOperacion = "Retiro",
                monto = 50.5,
                fecha = "17/10/2025 10:15",
                idUsuario = 101
            ),
            Operacion(
                idOperacion = 3,
                tipoOperacion = "Transferencia",
                monto = 200.0,
                fecha = "16/10/2025 09:00",
                idUsuario = 101
            ),
            Operacion(
                idOperacion = 4,
                tipoOperacion = "Pago de servicios",
                monto = 75.25,
                fecha = "15/10/2025 19:45",
                idUsuario = 101
            ),
            Operacion(
                idOperacion = 5,
                tipoOperacion = "Depósito",
                monto = 300.0,
                fecha = "14/10/2025 12:00",
                idUsuario = 101
            )
        )
        adapter = HistorialAdapter(listaOperaciones)
        recyclerView.adapter = adapter
        return view
    }

}