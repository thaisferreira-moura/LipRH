package com.example.demo

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Value

@Service
class ChatBotService {
    @Value("\${gemini.api.key:}")
    private var apiKey: String? = null

    fun chamarIA(pergunta: String): String {
        return "Serviço de IA carregado com sucesso."
    }
}