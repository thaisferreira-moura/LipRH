package com.example.lipsonrh

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File

class ExamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout fragment_exames
        val view = inflater.inflate(R.layout.fragment_exames, container, false)

        // Configuração dos exames pendentes baseados na imagem
        configurarExame(
            view.findViewById(R.id.exameHemograma),
            "Hemograma",
            "guia_hemograma.pdf"
        )

        configurarExame(
            view.findViewById(R.id.exameRaioX),
            "Raio-x",
            "guia_raio_x.pdf"
        )

        configurarExame(
            view.findViewById(R.id.exameEletro),
            "Eletrocardiograma",
            "guia_eletro.pdf"
        )

        return view
    }

    private fun configurarExame(item: View, nome: String, nomePdfAsset: String) {
        // Define o nome do exame no TextView
        item.findViewById<TextView>(R.id.txtNomeExame).text = nome

        // Configura o botão de baixar guia para abrir o PDF dos assets
        val btnBaixarGuia = item.findViewById<Button>(R.id.btnBaixarGuia)
        btnBaixarGuia.setOnClickListener {
            abrirPdfDosAssets(nomePdfAsset)
        }

        // Configura o botão de anexar (exemplo de funcionalidade)
        val btnAnexar = item.findViewById<Button>(R.id.btnAnexar)
        btnAnexar.setOnClickListener {
            Toast.makeText(context, "Abrindo galeria para: $nome", Toast.LENGTH_SHORT).show()
        }
    }

    private fun abrirPdfDosAssets(nomeArquivo: String) {
        try {
            // Copia o arquivo dos assets para o cache interno para permitir a visualização
            val arquivoCache = File(requireContext().cacheDir, nomeArquivo)

            if (!arquivoCache.exists()) {
                requireContext().assets.open(nomeArquivo).use { input ->
                    arquivoCache.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            }

            // Gera a URI segura via FileProvider configurado no Manifesto
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
            Toast.makeText(context, "Guia não encontrada nos arquivos", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}