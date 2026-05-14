package com.example.lipsonrh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsuariosCadastradosAdapter(private val lista: List<UsuarioItem>) :
    RecyclerView.Adapter<UsuariosCadastradosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.txtNomeFuncionario)
        val txtCpf: TextView = view.findViewById(R.id.txtCpfFuncionario)
        val txtOcupacao: TextView = view.findViewById(R.id.txtOcupacaoFuncionario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Você pode reutilizar o layout de item que já criamos e ajustar os IDs
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gestao_funcionario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.txtNome.text = item.nome
        holder.txtCpf.text = item.cpf
        holder.txtOcupacao.text = "Ocupação: ${item.ocupacao}"
    }

    override fun getItemCount() = lista.size
}