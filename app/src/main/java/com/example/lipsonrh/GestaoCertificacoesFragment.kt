package com.example.lipsonrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GestaoCertificacoesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gestao_beneficios, container, false)

        val txtTitulo = view.findViewById<TextView>(R.id.txtSaudacaoRH)
        txtTitulo?.text = "Gerenciar Certificações"

        val rv = view.findViewById<RecyclerView>(R.id.rvGestaoBeneficios)
        rv.layoutManager = LinearLayoutManager(context)

        val lista = listOf(
            CertificacaoItem("João Silva", "NR10 - Segurança", "Concluído"),
            CertificacaoItem("Marina Santos", "ISO 9001", "Em análise"),
            CertificacaoItem("Mauro Souza", "Brigada de Incêndio", "Vencido")
        )

        rv.adapter = CertificacaoAdapter(lista)
        return view
    }
}