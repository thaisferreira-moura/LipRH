package com.example.lipsonrh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast

class CalendarioFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("param1")
            param2 = it.getString("param2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos o layout
        val view = inflater.inflate(R.layout.fragment_calendario, container, false)

        // Referenciamos os componentes do XML
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val tvData = view.findViewById<TextView>(R.id.tvDataSelecionada)

        // Listener para capturar a mudança de data
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Nota: O mês (month) começa em 0 (Janeiro = 0), então somamos +1
            val dataFormatada = "$dayOfMonth/${month + 1}/$year"

            tvData.text = "Data selecionada: $dataFormatada"

            // Exemplo de ação ao selecionar
            Toast.makeText(context, "Data: $dataFormatada", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarioFragment().apply {
                arguments = Bundle().apply {
                    putString("param1", param1)
                    putString("param2", param2)
                }
            }
    }
}