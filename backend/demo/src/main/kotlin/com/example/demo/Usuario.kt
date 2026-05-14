package com.example.demo

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
class Usuario(
    @Id
    @Column(name = "cpf", length = 11) // Define o CPF como chave primária
    val cpf: String = "",
    
    @Column(name = "nome")
    val nome: String = "",
    
    @Column(name = "endereco")
    val endereco: String = "",
    
    @Column(name = "data_admissao")
    val dataAdmissao: String = "", 
    
    @Column(name = "cargo")
    val cargo: String = "", // "RH" ou "COLABORADOR"
    
    @Column(name = "senha")
    val senha: String = "",
    
    @Column(name = "data_nascimento")
    val dataNascimento: String? = null
)