package com.example.lipsonrh

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioService {
    // Esta rota deve bater exatamente com o @GetMapping que você fez no VSCode
    @GET("api/usuario/{cpf}/permissao-rh")
    fun verificarAcessoRH(@Path("cpf") cpf: String): Call<Boolean>
}