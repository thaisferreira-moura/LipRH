package com.example.lipsonrh

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import java.io.File

class BeneficiosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_beneficios, container, false)

        // Configuração dos cards baseada na imagem image_dae6fc.png

        configurarCard(
            view.findViewById(R.id.cardMedico),
            "Convênio Médico",
            "Plano de saúde para você e seus dependentes.",
            R.drawable.ic_medico,
            "https://www.amil.com.br",
            "FORMULARIO CANCELAMENTO AMIL 1.pdf",
            status = "ATIVO"
        )

        configurarCard(
            view.findViewById(R.id.cardOdonto),
            "Convênio Odontológico",
            "Plano odontológico para você e seus dependentes.",
            R.drawable.ic_dente,
            "https://www.amil.com.br",
            "FORMULARIO CANCELAMENTO AMIL 1.pdf",
            status = "ATIVO"
        )

        configurarCard(
            view.findViewById(R.id.cardTransporte),
            "Vale Transporte",
            "Benefício para deslocamento casa-trabalho.",
            R.drawable.ic_bus,
            "https://www.amil.com.br",
            "FORMULARIO CANCELAMENTO AMIL 1.pdf",
            status = "ATIVO"
        )

        configurarCard(
            view.findViewById(R.id.cardSesi),
            "Parceria SESI",
            "Benefício para educação de seu filho(a).",
            R.drawable.ic_sesi,
            "https://www.amil.com.br",
            "FORMULARIO CANCELAMENTO AMIL 1.pdf",
            status = "inativo" // Exemplo de status diferente da imagem
        )

        configurarCard(
            view.findViewById(R.id.cardSeguro),
            "Seguro de Vida",
            "Seguro de vida para você.",
            R.drawable.ic_seguro,
            "https://www.amil.com.br",
            "FORMULARIO CANCELAMENTO AMIL 1.pdf",
            status = "ATIVO"
        )

        configurarCard(
            view.findViewById(R.id.cardAlimentacao),
            "Cesta Básica",
            "Preencha o formulário.",
            R.drawable.ic_cesta,
            "https://www.amil.com.br",
            "Ficha cadastral cesta básica .pdf",
            status = "ATIVO"
        )

        return view
    }

    private fun configurarCard(
        card: View,
        titulo: String,
        subtitulo: String,
        iconeRes: Int,
        urlSite: String,
        nomePdfAsset: String,
        status: String
    ) {
        // Vincula os textos e ícone principal
        card.findViewById<TextView>(R.id.txtTituloBeneficio).text = titulo
        card.findViewById<TextView>(R.id.txtSubtituloBeneficio).text = subtitulo
        card.findViewById<ImageView>(R.id.imgBeneficioIcone).setImageResource(iconeRes)

        // Configura o Status Visual (Verde para Ativo, Vermelho para Inativo)
        val txtStatus = card.findViewById<TextView>(R.id.txtStatus)
        txtStatus.text = status.uppercase()

        if (status.lowercase() == "inativo") {
            txtStatus.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
            // Esconde botões que não fazem sentido para inativos
            card.findViewById<View>(R.id.btnCancelar)?.visibility = View.GONE
            card.findViewById<View>(R.id.btnAderir)?.visibility = View.GONE
        } else {
            txtStatus.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark))
        }

        // BOTÃO: Ver Detalhes (Texto clicável em vermelho conforme imagem)
        card.findViewById<TextView>(R.id.btnVerDetalhes).setOnClickListener {
            Toast.makeText(context, "Detalhes de $titulo", Toast.LENGTH_SHORT).show()
        }

        // BOTÃO: Solicitar Alterações (Abre Site)
        card.findViewById<Button>(R.id.btnSolicitar).setOnClickListener {
            abrirUrl(urlSite)
        }

        // BOTÃO: Cancelar (Abre PDF)
        card.findViewById<Button>(R.id.btnCancelar).setOnClickListener {
            abrirPdfDosAssets(nomePdfAsset)
        }

        // BOTÃO: Aderir (Ação de exemplo)
        card.findViewById<Button>(R.id.btnAderir)?.setOnClickListener {
            Toast.makeText(context, "Solicitação de adesão enviada!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun abrirUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Erro ao abrir o navegador", Toast.LENGTH_SHORT).show()
        }
    }

    private fun abrirPdfDosAssets(nomeArquivo: String) {
        try {
            val arquivoCache = File(requireContext().cacheDir, nomeArquivo)

            if (!arquivoCache.exists()) {
                requireContext().assets.open(nomeArquivo).use { input ->
                    arquivoCache.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            }

            val uri: Uri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.provider",
                arquivoCache
            )

            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "application/pdf")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)

            startActivity(intent)

        } catch (e: Exception) {
            Toast.makeText(context, "PDF não encontrado ou sem leitor instalado", Toast.LENGTH_SHORT).show()
        }
    }
}