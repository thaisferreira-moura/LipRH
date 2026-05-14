package com.example.lipsonrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Aqui você pode recuperar os dados do usuário logado (ex: Pedro Henrique Guerdis Silva)
        // e setar nos campos dinamicamente se necessário.
        val nomeUsuario = arguments?.getString("USER_NAME") ?: "Maria Ramos"
        val txtNome = view.findViewById<TextView>(R.id.txtNomePerfil)
        txtNome.text = nomeUsuario

        return view
    }
}