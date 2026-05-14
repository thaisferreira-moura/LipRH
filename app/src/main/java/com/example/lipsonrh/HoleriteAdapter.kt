package com.example.lipsonrh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HoleriteAdapter(private val lista: List<HoleriteItem>) :
    RecyclerView.Adapter<HoleriteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.txtNomeHolerite)
        val txtCpf: TextView = view.findViewById(R.id.txtCpfHolerite)
        val txtStatus: TextView = view.findViewById(R.id.txtStatusHolerite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gestao_holerite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.txtNome.text = item.nome
        holder.txtCpf.text = item.cpf
        holder.txtStatus.text = item.status

        // Lógica para mudar a cor do selo conforme o status da imagem image_caeedf.png
        if (item.status.equals("Adicionado", ignoreCase = true)) {
            holder.txtStatus.setBackgroundResource(R.drawable.shape_status_adicionado)
        } else {
            holder.txtStatus.setBackgroundResource(R.drawable.shape_status_pendente)
        }
    }

    override fun getItemCount() = lista.size
}