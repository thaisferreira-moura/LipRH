package com.example.lipsonrh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CertificacaoAdapter(private val lista: List<CertificacaoItem>) :
    RecyclerView.Adapter<CertificacaoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.txtNomeCert)
        val txtInfo: TextView = view.findViewById(R.id.txtCertInfo)
        val txtStatus: TextView = view.findViewById(R.id.txtStatusCert)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gestao_certificacao, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.txtNome.text = item.nome
        holder.txtInfo.text = item.curso
        holder.txtStatus.text = item.status

        // Lógica de cores para o Status da Certificação
        when (item.status.lowercase()) {
            "concluído" -> holder.txtStatus.setBackgroundResource(R.drawable.shape_status_validado)
            "vencido" -> holder.txtStatus.setBackgroundResource(R.drawable.shape_status_pendente)
            "em análise" -> holder.txtStatus.setBackgroundResource(R.drawable.shape_status_validar)
            else -> holder.txtStatus.setBackgroundResource(R.drawable.shape_status_enviado)
        }
    }

    override fun getItemCount() = lista.size
}