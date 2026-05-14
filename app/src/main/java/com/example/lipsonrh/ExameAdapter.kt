package com.example.lipsonrh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExameAdapter(private val lista: List<ExameItem>) :
    RecyclerView.Adapter<ExameAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.txtNomeExame)
        val txtCpf: TextView = view.findViewById(R.id.txtCpfExame)
        val txtStatusEnvio: TextView = view.findViewById(R.id.txtStatusEnvio)
        val txtStatusValidacao: TextView = view.findViewById(R.id.txtStatusValidacao)
        val btnVer: ImageView = view.findViewById(R.id.btnVerExame)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gestao_exame, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.txtNome.text = item.nome
        holder.txtCpf.text = item.cpf
        holder.txtStatusEnvio.text = item.statusEnvio
        holder.txtStatusValidacao.text = item.statusValidacao

        // Lógica de cores para o Status de ENVIO
        when (item.statusEnvio.lowercase()) {
            "enviado" -> holder.txtStatusEnvio.setBackgroundResource(R.drawable.shape_status_enviado)
            "pendente" -> holder.txtStatusEnvio.setBackgroundResource(R.drawable.shape_status_pendente)
        }

        // Lógica de cores para o Status de VALIDAÇÃO
        when (item.statusValidacao.lowercase()) {
            "validado" -> holder.txtStatusValidacao.setBackgroundResource(R.drawable.shape_status_validado)
            "validar" -> holder.txtStatusValidacao.setBackgroundResource(R.drawable.shape_status_validar)
        }
    }

    override fun getItemCount() = lista.size
}