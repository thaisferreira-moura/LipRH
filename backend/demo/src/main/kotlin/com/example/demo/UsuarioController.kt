package com.example.demo // Verifique se este é o pacote correto do seu projeto

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = ["*"]) // Essencial para permitir que o emulador Android acesse a API
class UsuarioController(val repository: UsuarioRepository) {

    // 1. BUSCAR USUÁRIO POR CPF (Usado no Login)
    // Rota: GET http://localhost:8080/api/usuario/{id}
    @GetMapping("/{id}")
    fun obterUsuario(@PathVariable id: String): ResponseEntity<Usuario> {
        return repository.findById(id)
            .map { usuario -> ResponseEntity.ok(usuario) }
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
    }

    // 2. CADASTRAR NOVO USUÁRIO (Usado na Gestão de Funcionários)
    // Rota: POST http://localhost:8080/api/usuario
    @PostMapping
    fun criarUsuario(@RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        return try {
            val usuarioSalvo = repository.save(usuario)
            ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }

    // 3. VERIFICAR PERMISSÃO DE RH
    // Rota: GET http://localhost:8080/api/usuario/{cpf}/permissao-rh
    @GetMapping("/{cpf}/permissao-rh")
    fun temAcessoRH(@PathVariable cpf: String): ResponseEntity<Boolean> {
        val usuario = repository.findById(cpf).orElse(null)
        
        // Verifica se o usuário existe e se o cargo é "RH"
        val possuiAcesso = usuario?.cargo?.equals("RH", ignoreCase = true) ?: false
        
        return ResponseEntity.ok(possuiAcesso)
    }

    // 4. LISTAR TODOS OS USUÁRIOS (Opcional - Útil para preencher o RecyclerView)
    @GetMapping
    fun listarTodos(): List<Usuario> {
        return repository.findAll()
    }
}