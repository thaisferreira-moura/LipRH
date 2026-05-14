package com.example.lipsonrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GestaoBeneficiosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gestao_beneficios, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvGestaoBeneficios)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // DADOS DE TESTE (Simulando o que viria do MySQL)
        val dadosTeste = listOf(
            FuncionarioBeneficio("João Silva", "123.456.789-00", "Odonto: cancelamento"),
            FuncionarioBeneficio("Marina Santos", "987.654.321-11", "Formulário cadastral cesta básica"),
            FuncionarioBeneficio("Pedro Guerdis", "111.222.333-44", "Convênio: Preenchimento")
        )

        // Conecta o Adapter ao RecyclerView
        recyclerView.adapter = BeneficioAdapter(dadosTeste)

        return view
    }
}