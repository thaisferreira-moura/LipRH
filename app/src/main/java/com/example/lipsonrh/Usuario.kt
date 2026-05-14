package com.example.lipsonrh

import com.google.gson.annotations.SerializedName

data class Usuario(
    val cpf: String,
    val nome: String,
    val endereco: String,

    // O @SerializedName faz a ponte entre o "data_admissao" do MySQL
    // e o "dataAdmissao" do seu código Kotlin.
    @SerializedName("data_admissao")
    val dataAdmissao: String,

    val cargo: String,
    val senha: String,

    // Adicionado para bater com o seu novo script SQL
    @SerializedName("data_nascimento")
    val dataNascimento: String? = null
)