package com.example.demo

import jakarta.persistence.*

@Entity
@Table(name = "faq")
class Faq(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(columnDefinition = "TEXT")
    val pergunta: String = "",

    @Column(columnDefinition = "TEXT")
    val resposta: String = ""
)