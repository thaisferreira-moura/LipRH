package com.example.demo

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import java.text.Normalizer

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = ["*"])
class ChatBotController(val faqRepository: FaqRepository) {

    @PostMapping("/perguntar")
    fun responderPergunta(@RequestBody perguntaUsuario: String): ResponseEntity<String> {
        // 1. Limpa aspas e normaliza o texto (remove acentos e interrogações)
        val perguntaTratada = normalizarTexto(perguntaUsuario)

        // 2. Busca o FAQ
        val baseConhecimento = faqRepository.findAll()
        
        // 3. Busca Inversa: Verifica se a "pergunta" do banco está contida na frase do usuário
        // Ex: Se o usuário diz "como pedir férias", ele vai encontrar a palavra "ferias" no banco.
        val respostaEncontrada = baseConhecimento.find { 
            val perguntaBancoNormalizada = normalizarTexto(it.pergunta)
            perguntaTratada.contains(perguntaBancoNormalizada, ignoreCase = true) 
        }?.resposta ?: "Desculpe, ainda estou aprendendo sobre isso. Gostaria de falar com o RH?"

        return ResponseEntity.ok(respostaEncontrada)
    }

    @GetMapping("/status")
    fun status(): ResponseEntity<String> = ResponseEntity.ok("O serviço de Chatbot da Lipson está ativo!")

    // Função auxiliar para remover acentos, pontos e converter para minúsculo
    private fun normalizarTexto(texto: String): String {
        val semAspas = texto.replace("\"", "")
        val temp = Normalizer.normalize(semAspas, Normalizer.Form.NFD)
        return temp.replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
            .replace("?", "")
            .replace(".", "")
            .lowercase()
            .trim()
    }
}