package com.example.demo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository : JpaRepository<Usuario, String> {
    // O JpaRepository já nos dá o findById(cpf), save(usuario) e findAll() por padrão.
}