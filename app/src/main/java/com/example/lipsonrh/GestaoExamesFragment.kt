package com.example.lipsonrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GestaoExamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gestao_beneficios, container, false)

        val txtTitulo = view.findViewById<TextView>(R.id.txtSaudacaoRH)
        txtTitulo?.text = "Visualize o status dos exames"

        val rv = view.findViewById<RecyclerView>(R.id.rvGestaoBeneficios)
        rv.layoutManager = LinearLayoutManager(context)

        // Dados baseados na imagem image_ca81c4.png
        val listaExames = listOf(
            ExameItem("João Silva", "123.456.789-00", "Pendente", "Validar"),
            ExameItem("Marina Santos", "123.456.789-00", "Enviado", "Validado"),
            ExameItem("Mauro Souza", "123.456.789-00", "Enviado", "Validar")
        )

        rv.adapter = ExameAdapter(listaExames)
        return view
    }
}