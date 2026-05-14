package com.example.lipsonrh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BeneficioAdapter(private val lista: List<FuncionarioBeneficio>) :
    RecyclerView.Adapter<BeneficioAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome = view.findViewById<TextView>(R.id.txtNomeFuncionario)
        val txtCpf = view.findViewById<TextView>(R.id.txtCpfFuncionario)
        val txtStatus = view.findViewById<TextView>(R.id.txtStatusBeneficio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gestao_funcionario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.txtNome.text = item.nome
        holder.txtCpf.text = item.cpf
        holder.txtStatus.text = item.statusBeneficio
    }

    override fun getItemCount() = lista.size
}