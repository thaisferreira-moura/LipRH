package com.example.lipsonrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView // Importação que estava faltando
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GestaoHoleritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Reutilizamos o layout base fragment_gestao_beneficios
        val view = inflater.inflate(R.layout.fragment_gestao_beneficios, container, false)

        // 1. Configura o Título da Página (image_caeedf.png)
        // Certifique-se de que o ID no XML seja txtSaudacaoRH ou o ID correto do título
        val txtTitulo = view.findViewById<TextView>(R.id.txtSaudacaoRH)
        txtTitulo?.text = "Adicione os holerites"

        // 2. Configura o RecyclerView
        val rv = view.findViewById<RecyclerView>(R.id.rvGestaoBeneficios)
        rv.layoutManager = LinearLayoutManager(context)

        // 3. Dados de exemplo conforme a imagem image_caeedf.png
        val listaHolerites: List<HoleriteItem> = listOf(
            HoleriteItem("João Silva", "123.456.789-00", "Pendente"),
            HoleriteItem("Marina Santos", "123.456.789-00", "Adicionado"),
            HoleriteItem("Mauro Souza", "123.456.789-00", "Pendente")
        )

        // 4. Define o Adapter (Você precisará criar o HoleriteAdapter)
        rv.adapter = HoleriteAdapter(listaHolerites)

        return view
    }
}