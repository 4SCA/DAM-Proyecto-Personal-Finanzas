package com.adrianoca.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adrianoca.myapplication.R
import com.adrianoca.myapplication.entity.Operacion
import java.text.SimpleDateFormat
import java.util.Locale

class HistorialAdapter(private var listaOperaciones: List<Operacion>) :
    RecyclerView.Adapter<HistorialAdapter.ViewHolder>() {

    private var onItemClickListener: ((Operacion) -> Unit)? = null

    fun setOnItemClickListener(listener: (Operacion) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historial, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val operacion = listaOperaciones[position]
        holder.tvIdLista.text = "Operacion #${String.format("%03d", operacion.idOperacion)}"

        val fechaFormateada = try {
            val parser = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val date = parser.parse(operacion.fecha)
            SimpleDateFormat("dd MMM yyyy", Locale("es", "PE")).format(date!!)
        } catch (e: Exception) {
            e.printStackTrace()
            operacion.fecha
        }

        holder.tvFecha.text = "Fecha: $fechaFormateada"
        holder.tvMonto.text = "Monto: S/. ${String.format("%.2f", operacion.monto)}"
        holder.tvTipoOperacion.text = operacion.tipoOperacion

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(operacion)
        }
    }

    override fun getItemCount(): Int = listaOperaciones.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIdLista: TextView = itemView.findViewById(R.id.tvIdLista)
        val tvTipoOperacion: TextView = itemView.findViewById(R.id.tvTipoOperacion)
        val tvMonto: TextView = itemView.findViewById(R.id.tvMonto)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        val ivDetalle: ImageView = itemView.findViewById(R.id.ivDetalle)
    }
}