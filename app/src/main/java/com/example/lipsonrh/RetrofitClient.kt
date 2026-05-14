package com.example.lipsonrh

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://seu-projeto-no-render.onrender.com/" // IP padrão do emulador para o localhost

    val instance: UsuarioApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(UsuarioApiService::class.java)
    }
}