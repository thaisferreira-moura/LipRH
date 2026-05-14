package com.example.lipsonrh

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioApiService {
    @GET("api/usuario/{cpf}")
    fun buscarUsuarioPorCpf(@Path("cpf") cpf: String): Call<Usuario>

    @POST("api/usuario")
    fun cadastrarUsuario(@Body usuario: Usuario): Call<Usuario>

    // NOVO: Rota para o Chatbot enviar a pergunta ao Spring Boot
    @POST("api/chatbot/perguntar")
    fun perguntarChatbot(@Body pergunta: String): Call<String>
}