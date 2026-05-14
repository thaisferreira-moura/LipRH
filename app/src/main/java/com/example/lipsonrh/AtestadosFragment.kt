package com.example.lipsonrh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class AtestadosFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_atestados, container, false)

        view.findViewById<Button>(R.id.btnEnviarAtestado).setOnClickListener {
            Toast.makeText(context, "Atestado enviado com sucesso!", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<View>(R.id.areaUpload).setOnClickListener {
            Toast.makeText(context, "Abrindo seletor de arquivos...", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}