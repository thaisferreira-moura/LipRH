package com.example.lipsonrh

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("api/auth/login")
    fun login(@Body credenciais: Map<String, String>): Call<LoginResponse>

    @GET("api/usuario/{id}")
    fun getUsuario(@Path("id") id: String): Call<UsuarioResponse>
}

// Defina as classes APENAS UMA VEZ aqui embaixo
data class LoginResponse(
    val tipo: String,
    val nome: String,
    val erro: String? = null
)

data class UsuarioResponse(
    val cpf: String,
    val nome: String,
    val endereco: String,
    val dataAdmissao: String,
    val cargo: String,
    val senha: String
)