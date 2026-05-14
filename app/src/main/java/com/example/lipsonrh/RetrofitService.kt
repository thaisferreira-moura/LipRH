package com.example.lipsonrh

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {


    @GET("api/usuario/{cpf}")
    fun buscarUsuarioPorCpf(@Path("cpf") cpf: String): Call<Usuario>
}