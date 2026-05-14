package com.example.lipsonrh

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeRHFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_rh, container, false)

        // Recupera o nome do usuário enviado pela MainActivity
        // Pedro Henrique Guerdis Silva
        val nomeUsuario = arguments?.getString("USER_NAME") ?: "PEDRO GUERDIS"
        val txtSaudacao = view.findViewById<TextView>(R.id.txtSaudacaoRH)
        txtSaudacao.text = "OLÁ, ${nomeUsuario.uppercase()}"

        // Configuração dos itens do menu conforme a imagem d82965
        setupMenu(
            view.findViewById(R.id.btnRhuBeneficios),
            "Benefícios",
            R.drawable.beneficios
        )
        setupMenu(
            view.findViewById(R.id.btnRhuHolerites),
            "Holerites",
            R.drawable.holerites
        )
        setupMenu(
            view.findViewById(R.id.btnRhuExames),
            "Exames",
            R.drawable.exames
        )
        setupMenu(
            view.findViewById(R.id.btnRhuCertificacoes),
            "Certificações",
            R.drawable.certificacao
        )

        return view
    }

    private fun setupMenu(item: View, titulo: String, icone: Int) {
        val txtTitulo = item.findViewById<TextView>(R.id.txtMenuTitulo)
        val imgIcone = item.findViewById<ImageView>(R.id.imgMenuIcone)

        txtTitulo.text = titulo
        imgIcone.setImageResource(icone)

        item.setOnClickListener {
            Log.d("CLIQUE_RH", "Clicou em: $titulo")

            if (titulo == "Benefícios") {
                // Navega para a tela de Gestão de Benefícios (imagem image_cb7d28.png)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, GestaoBeneficiosFragment())
                    .addToBackStack(null) // Permite que o usuário use o botão "voltar"
                    .commit()
            } else {
                Toast.makeText(context, "Área de $titulo em desenvolvimento", Toast.LENGTH_SHORT).show()
            }
        }
        item.setOnClickListener {
            val destino = when (titulo) {
                "Benefícios" -> GestaoBeneficiosFragment()
                "Holerites" -> GestaoHoleritesFragment()
                "Exames" -> GestaoExamesFragment()
                "Certificações" -> GestaoCertificacoesFragment()
                else -> null
            }

            if (destino != null) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, destino)
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(context, "Área de $titulo em desenvolvimento", Toast.LENGTH_SHORT).show()
            }
        }
    }
}